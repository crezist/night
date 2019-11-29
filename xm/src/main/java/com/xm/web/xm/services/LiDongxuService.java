package com.xm.web.xm.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.web.xm.mapper.App_LiDongxuMapper;
import com.xm.web.xm.mapper.GoodscollectionMapper;
import com.xm.web.xm.mapper.GoodsimageMapper;
import com.xm.web.xm.mapper.GoodsinfoMapper;
import com.xm.web.xm.mapper.GoodspriceMapper;
import com.xm.web.xm.mapper.StoreuserMapper;
import com.xm.web.xm.pojo.GoodscollectionExample;
import com.xm.web.xm.pojo.GoodscollectionKey;
import com.xm.web.xm.pojo.Goodsimage;
import com.xm.web.xm.pojo.GoodsimageExample;
import com.xm.web.xm.pojo.Goodsinfo;
import com.xm.web.xm.pojo.GoodsinfoExample;
import com.xm.web.xm.pojo.Goodsprice;
import com.xm.web.xm.pojo.GoodspriceExample;
import com.xm.web.xm.pojo.Storeuser;
import com.xm.web.xm.pojo.StoreuserExample;

@Service
public class LiDongxuService {

	@Autowired
	GoodscollectionMapper goodscollectionMapper;

	@Autowired
	App_LiDongxuMapper app_LiDongxuMapper;

	@Autowired
	GoodsinfoMapper goodsinfoMapper;

	@Autowired
	GoodspriceMapper goodspriceMapper;

	@Autowired
	GoodsimageMapper goodsimageMapper;
	
	@Autowired
	StoreuserMapper storeuserMapper;
	
	/**
	 * 查询商品名称是否重复
	 * @param gdname 商品名称
	 * @return
	 */
	public boolean selectgdname(String gdname){
		GoodsinfoExample example=new GoodsinfoExample();
		example.createCriteria().andGdnameEqualTo(gdname);
		return goodsinfoMapper.selectByExample(example).size()!=0;
	}
	
	/**
	 * 添加商品全部信息
	 * @param gdname 商品名字
	 * @param stid 店铺编号
	 * @param gtkeywords 商品关键字
	 * @param gimgurl 商品图片路径
	 * @param price 商品价格
	 * @return
	 */
	@Transactional
	public boolean insertgoodsinfo(String gdname,Integer stid,String gtkeywords,String gimgurl,Double price){
		Goodsinfo goods=new Goodsinfo();
		goods.setGdname(gdname);
		goods.setStid(stid);
		goods.setGtkeywords(gtkeywords);
		goods.setGtdate(new Date());
		Integer g=goodsinfoMapper.insertSelective(goods);
		if(g!=0){
			GoodsinfoExample example=new GoodsinfoExample();
			example.createCriteria().andGdnameEqualTo(gdname).andStidEqualTo(stid);
			Integer gdid=goodsinfoMapper.selectByExample(example).get(0).getGdid();
			
			Goodsimage img=new Goodsimage();
			img.setGimgurl(gimgurl);
			img.setGdid(gdid);
			img.setGimgtype(1);
			goodsimageMapper.insertSelective(img);
			
			
			Goodsprice aprice=new Goodsprice();
			aprice.setGdid(gdid);
			aprice.setUtid(1);
			aprice.setPrice(price);
			goodspriceMapper.insertSelective(aprice);
			
			return true;
		}
		return false;
	}
	
	
	/**
	 * 通过userid查询店铺编号
	 * @param userid 用户id
	 * @return
	 */
	public Integer selectstoreid(Integer userid){
		StoreuserExample example=new StoreuserExample();
		example.createCriteria().andUseridEqualTo(userid);
		List<Storeuser> stur=storeuserMapper.selectByExample(example);
		Integer ss=stur.get(0).getStid();
		return ss;
	}
	
	
	/**
	 * 店铺更改商品信息
	 * @param gdid 商品编号
	 * @param gdname 商品名字
	 * @param gimgurl 商品图片路径
	 * @param price 商品价格
	 * @return
	 */
	@Transactional
	public boolean updategoodsinfo(Integer gdid,String gdname,String gimgurl,Double price){
		Goodsimage img=new Goodsimage();
		img.setGimgurl(gimgurl);
		GoodsimageExample example_img=new GoodsimageExample();
		example_img.createCriteria().andGdidEqualTo(gdid).andGimgtypeEqualTo(1);
		goodsimageMapper.updateByExampleSelective(img, example_img);
		
		Goodsprice sprice=new Goodsprice();
		sprice.setPrice(price);
		GoodspriceExample example_price=new GoodspriceExample();
		example_price.createCriteria().andGdidEqualTo(gdid).andUtidEqualTo(1);
		goodspriceMapper.updateByExampleSelective(sprice, example_price);
		
		Goodsinfo goods=new Goodsinfo();
		goods.setGdname(gdname);
		GoodsinfoExample example=new GoodsinfoExample();
		example.createCriteria().andGdidEqualTo(gdid);
		goodsinfoMapper.updateByExampleSelective(goods, example);
		
		return true;
	}

	/**
	 * 查看商品相关信息
	 * 
	 * @param gdid
	 *            商品编号
	 * @return
	 */
	public Map getGoodsinfo(Integer gdid) {
		return app_LiDongxuMapper.selectgoodsinfo(gdid);

	}

	/**
	 * 删除商品信息
	 * 
	 * @param gdid
	 * @return
	 */
	@Transactional
	public boolean delectgoodsinfo(Integer gdid) {
		GoodsimageExample example_img = new GoodsimageExample();
		example_img.createCriteria().andGdidEqualTo(gdid);
		 Integer img=goodsimageMapper.deleteByExample(example_img);
		if (img!=0) {
			GoodspriceExample example_price = new GoodspriceExample();
			example_price.createCriteria().andGdidEqualTo(gdid);
			Integer price=goodspriceMapper.deleteByExample(example_price);
			if (price!=0) {
				GoodscollectionExample example_count=new GoodscollectionExample();
				example_count.createCriteria().andGdidEqualTo(gdid);
				GoodsinfoExample example = new GoodsinfoExample();
				example.createCriteria().andGdidEqualTo(gdid);
				Integer count=(int) goodscollectionMapper.countByExample(example_count);
				if(count==0){
					goodsinfoMapper.deleteByExample(example);
					return true;
				}else{
					GoodscollectionExample example_collection = new GoodscollectionExample();
					example_collection.createCriteria().andGdidEqualTo(gdid);
					Integer coll=goodscollectionMapper.deleteByExample(example_collection);
					if (coll!=0) {
						goodsinfoMapper.deleteByExample(example);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 查询所有收藏商品
	 * 
	 * @param userid
	 *            用户id
	 * @return
	 */
	public List<Map> selectAllcollection(Integer userid) {
		List<Map> conllection = app_LiDongxuMapper.getgoodscollection(userid);
		return conllection;
	}

	/**
	 * 查询是否收藏该商品
	 * 
	 * @param gdid
	 *            商品编号
	 * @param userid
	 *            用户id
	 * @return
	 */
	public boolean selectGoodscollection(Integer gdid, Integer userid) {
		GoodscollectionExample example = new GoodscollectionExample();
		example.createCriteria().andGdidEqualTo(gdid).andUseridEqualTo(userid);
		List<GoodscollectionKey> gg = goodscollectionMapper.selectByExample(example);
		return gg.size() == 0;
	}

	/**
	 * 添加收藏
	 * 
	 * @param goodskey
	 *            添加收藏记录
	 * @return
	 */
	@Transactional
	public boolean insertgoodscollectionMapper(GoodscollectionKey goodskey) {
		return goodscollectionMapper.insert(goodskey) == 1;
	}

	/**
	 * 删除收藏
	 * 
	 * @param gdid
	 *            商品编号
	 * @param userid
	 *            用户id
	 * @return
	 */
	@Transactional
	public boolean delectgoodscollec(Integer gdid, Integer userid) {
		GoodscollectionExample example = new GoodscollectionExample();
		example.createCriteria().andGdidEqualTo(gdid).andUseridEqualTo(userid);
		return goodscollectionMapper.deleteByExample(example) == 1;
	}

}
