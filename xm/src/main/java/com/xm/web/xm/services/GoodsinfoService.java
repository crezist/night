package com.xm.web.xm.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xm.web.xm.AlipayConfig;
import com.xm.web.xm.exception.NameIsNotExistException;
import com.xm.web.xm.exception.PasswordIsErrorException;
import com.xm.web.xm.exception.UserIsDisabledException;
import com.xm.web.xm.mapper.App_BrowsercartMapper;
import com.xm.web.xm.mapper.App_GetOrderMapper;
import com.xm.web.xm.mapper.App_GoodsinfoMapper;
import com.xm.web.xm.mapper.App_OrderMapper;
import com.xm.web.xm.mapper.CartMapper;
import com.xm.web.xm.mapper.GoodsinfoMapper;
import com.xm.web.xm.mapper.GoodssizeMapper;
import com.xm.web.xm.mapper.OrderinfoMapper;
import com.xm.web.xm.mapper.OrderlistMapper;
import com.xm.web.xm.mapper.UserMapper;
import com.xm.web.xm.pojo.Cart;
import com.xm.web.xm.pojo.CartExample;
import com.xm.web.xm.pojo.Goodsinfo;
import com.xm.web.xm.pojo.GoodsinfoExample;
import com.xm.web.xm.pojo.Goodssize;
import com.xm.web.xm.pojo.GoodssizeExample;
import com.xm.web.xm.pojo.Orderinfo;
import com.xm.web.xm.pojo.OrderinfoExample;
import com.xm.web.xm.pojo.Orderlist;
import com.xm.web.xm.pojo.OrderlistExample;
import com.xm.web.xm.pojo.User;
import com.xm.web.xm.pojo.UserExample;

@Service
public class GoodsinfoService {
	
	@Autowired
	private App_GoodsinfoMapper Mapper;
	
	
	@Autowired
	private GoodsinfoMapper Gmapper;
	
	@Autowired
	private GoodssizeMapper GoodsSizeMapper;
	
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private App_BrowsercartMapper browsercartmapper; 
	
	@Autowired
	private OrderinfoMapper orderinfomapper; 
	
	@Autowired
	private OrderlistMapper orderlistmapper;
	
	@Autowired
	private App_OrderMapper ordermapper;
	
	@Autowired
	private App_GetOrderMapper getordermapper;
	/**
	 * 支付后改变订单状态并删除购物车
	 * @param ofid 订单编号
	 * @return 
	 */
	
	
	public List<Map> getorderdetails(Integer userid){
		return getordermapper.getorder(userid);
		
	}
	
	
	@Transactional
	public void paydone(String ofid){
		Orderinfo orderinfo=orderinfomapper.selectByPrimaryKey(ofid);
		orderinfo.setOfstate(2);
		orderinfomapper.updateByPrimaryKeySelective(orderinfo);
		
		OrderlistExample example=new OrderlistExample();
		example.createCriteria().andOfidEqualTo(ofid);
		List<Orderlist> list=orderlistmapper.selectByExample(example);
		for (Orderlist orderlist : list) {
			CartExample cart=new CartExample();
			cart.createCriteria().andUseridEqualTo(orderinfo.getUserid()).andGdidEqualTo(orderlist.getGdid()).andGsidEqualTo(orderlist.getGsid());
			cartMapper.deleteByExample(cart);	
		}
	}
	
	
	
	public void ali(HttpServletResponse response,String ofid) throws AlipayApiException, IOException{
		  //设置编码
		  response.setContentType("text/html;charset=utf-8");

		  PrintWriter out = response.getWriter();
		  //获得初始化的AlipayClient
		  AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		  //设置请求参数
		  AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
		  aliPayRequest.setReturnUrl(AlipayConfig.return_url);
		  aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
		  
		  //商户订单号，后台可以写一个工具类生成一个订单号，必填
		  String order_number = new String(ofid);
		  //付款金额，从前台获取，必填
		  String total_amount = new String(this.getsum(ofid).toString());
		  //订单名称，必填
		  String subject = new String("支付宝沙箱支付（名称随便起）");
		  aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
		    + "\"total_amount\":\"" + total_amount + "\","
		    + "\"subject\":\"" + subject + "\","
		    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		  //请求
		  String result = alipayClient.pageExecute(aliPayRequest).getBody();
		  //输出
		  out.println(result);//以下写自己的订单代码
		}	
	
	public Double getsum(String ofid){
		return ordermapper.getsum(ofid);
	}
	
	public List<Orderinfo> getorder(Integer userid){
		OrderinfoExample example=new OrderinfoExample();
		example.createCriteria().andUseridEqualTo(userid);
		return orderinfomapper.selectByExample(example);
	}
	
	
	
	
	@Transactional
	public void makeOrder(Orderinfo orderinfo,List<Integer> ctids){
		String ofid=UUID.randomUUID().toString();
		orderinfo.setOfid(ofid);
		orderinfo.setOfstate(1);
		orderinfo.setOfdate(new Date());
        orderinfomapper.insert(orderinfo);
        
        List<Orderlist> list=ordermapper.makeOrder(ctids);
        for (int i = 0;i <list.size(); i++) {
        	 Orderlist orderlist=list.get(i);
        	 orderlist.setOfid(ofid);
             orderlist.setOlid(ofid+"_"+i);
     		orderlistmapper.insert(orderlist);	
		}
	}
	
	
	
	@Transactional
	public void changecount(Cart cart){
		if(cart.getGdcount()<=0){
			cartMapper.deleteByPrimaryKey(cart.getCtid());
		}else{
			cartMapper.updateByPrimaryKeySelective(cart);
		}
		
	}
	
	
	 public List<Map> getcart(Integer userid){
		 return browsercartmapper.getcart(userid);   
	 }

	@Transactional
	public void Tocart(Cart cart){
		CartExample example=new CartExample();
		example.createCriteria().andUseridEqualTo(cart.getUserid()).andGdidEqualTo(cart.getGdid()).andGsidEqualTo(cart.getGsid());
		List<Cart> list=cartMapper.selectByExample(example);
	    if(list!=null&&list.size()==1){
	      Cart cartt=list.get(0);
	      cartt.setGdcount(cartt.getGdcount()+cart.getGdcount());
	      cartMapper.updateByPrimaryKey(cartt);
	    }else{
	      cartMapper.insert(cart);
	    }

		
	}
	
	
	public boolean sameName(String logname){
		UserExample example=new UserExample();
		example.createCriteria().andLognameEqualTo(logname);
		return usermapper.countByExample(example)==0;
	}
	
	
	
    public User loginin(String logname,String password) throws NameIsNotExistException, UserIsDisabledException, PasswordIsErrorException{
    	UserExample example=new UserExample();
    	example.createCriteria().andLognameEqualTo(logname);
		List<User> list=usermapper.selectByExample(example);
	    if(list==null||list.size()==0){
	    	throw new NameIsNotExistException();
	    }
	    User user=list.get(0);
	    if(user.getStatus()==0){
	    	throw new UserIsDisabledException();
	    }
	    String client_password=new Md5Hash(password,user.getSalt(),2).toString();
		if(!client_password.equals(user.getPassword())){
			throw new PasswordIsErrorException();
		}
    	return user;
    }
	
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@Transactional
	public boolean login(User user){
		String pwd=user.getPassword();
		String salt=UUID.randomUUID().toString();
		user.setPassword(new Md5Hash(pwd,salt,2).toString());
		user.setSalt(salt);
		user.setStatus(1);
		user.setUtid(1);
		return usermapper.insert(user)==1;
	}
	
	
	public List<Goodssize> getallgoodssize(){
		GoodssizeExample example=new GoodssizeExample();
		return GoodsSizeMapper.selectByExample(example);
	}
	
	
	public Map getDetails(Integer gdid){
		return Mapper.getDetail(gdid);
	}
	
	public List<Map> getGoodsinfo(Integer limit,Integer current){
		GoodsinfoExample example=new GoodsinfoExample();
		RowBounds rowBounds=new RowBounds((current-1)*limit, limit);
		return Mapper.getGoodsinfoWithRowbounds(example, rowBounds);
		 
	}
	
	//
	public List<Goodsinfo> getgoodsinfoByLike(String keyword){
		GoodsinfoExample example=new GoodsinfoExample();
		example.createCriteria().andGdnameLike("%"+keyword+"%");
		example.setOrderByClause("gtdate desc");
		RowBounds rowBounds=new RowBounds(0, 10);
		return Gmapper.selectByExampleWithRowbounds(example, rowBounds);
	}
	
	
	
	public Integer getPages(Integer limit){
		GoodsinfoExample example=new GoodsinfoExample();
		example.createCriteria().andGdidLessThanOrEqualTo(240);
		Integer count=(int)Gmapper.countByExample(example);	
		return (count%limit)==0?(count/limit):(count/limit+1);
	}
	
	

}
