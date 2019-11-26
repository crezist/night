package com.xm.web.xm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xm.web.xm.AlipayConfig;
import com.xm.web.xm.exception.NameIsNotExistException;
import com.xm.web.xm.exception.PasswordIsErrorException;
import com.xm.web.xm.exception.UserIsDisabledException;
import com.xm.web.xm.pojo.Cart;
import com.xm.web.xm.pojo.Goodsinfo;
import com.xm.web.xm.pojo.Orderinfo;
import com.xm.web.xm.pojo.Typeinfo;
import com.xm.web.xm.pojo.User;
import com.xm.web.xm.services.GoodsinfoService;
import com.xm.web.xm.services.TypeinfoService;
import com.xm.web.xm.services.TypeinfoService;

@RestController
@RequestMapping(value="/Goodsinfoctl")
public class GoodsinfoCtl {
	
	@Autowired
	private GoodsinfoService service;
	
	

	

	@RequestMapping("getorderdetails")
	public List<Map> getorderdetails(HttpSession session){
		User user=(User) session.getAttribute("user");
		return service.getorderdetails(user.getUserid());
	}
	
	@RequestMapping("getorder")
    public List<Orderinfo> getorder(HttpSession session){
	  User user=(User) session.getAttribute("user");
		return service.getorder(user.getUserid());
	}
	
	@RequestMapping("makeorder")
	public boolean makeorder(Orderinfo orderinfo,@RequestParam(name="ctids")List<Integer> ctids,HttpSession session){
		 User user=(User) session.getAttribute("user");
		 orderinfo.setUserid(user.getUserid());
		 service.makeOrder(orderinfo, ctids);	
		 return true;
	}
	
	
	@RequestMapping("changecount")
	public boolean changeout(Cart cart){
		service.changecount(cart);
		return true;
	}
	
	
	@RequestMapping("browsercart")
	public List<Map> browsercart(HttpSession session){
		User user=(User) session.getAttribute("user");
		return service.getcart(user.getUserid());
	}
	
	
	@RequestMapping("tocart")
	public boolean tocart(Cart cart,HttpSession session){
		User user=(User) session.getAttribute("user");
		cart.setUserid(user.getUserid());
		service.Tocart(cart);
		return true;
	}
	
	@RequestMapping("logout")
	public Object logout(HttpSession session){
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
			return true;
		}
		return false;
		
		
	}
	
	@RequestMapping("useronline")
	public Object useronline(HttpSession session){
		return session.getAttribute("user");
	}
	
	
	
	@RequestMapping("login")
	public Object login(String password,String logname,HttpSession session){
	    try {
			User user=service.loginin(logname, password);
			session.setAttribute("user", user);
			return 0;
		} catch (NameIsNotExistException e) {
			e.printStackTrace();
			return 1;
		} catch (UserIsDisabledException e) {
			e.printStackTrace();
			return 2;
		} catch (PasswordIsErrorException e) {
			e.printStackTrace();
			return 3;
		}
	}
	
	@RequestMapping("register")
	public boolean register(User user){
		return service.login(user);
	}
	
	@RequestMapping("lognameisok")
	public boolean lognameisok(String logname){
		return service.sameName(logname);
	}
	
	@RequestMapping("getallsize")
	public Object getAllsize(){
		return service.getallgoodssize();
	}
	
	@RequestMapping("getdetails")
	public Object getDetails(Integer gdid){
		return service.getDetails(gdid);
	}
	
	@RequestMapping("like")
	public List<Goodsinfo> getLike(String keyword){
		return service.getgoodsinfoByLike(keyword);
		
	}
	
	@RequestMapping("goods")
	public Object goods(Integer current){
		Integer limit=20;
		Map re=new HashMap();
		re.put("goodsinfo",service.getGoodsinfo(limit, current));
		re.put("pages",service.getPages(limit));
		return re;
	}

}
