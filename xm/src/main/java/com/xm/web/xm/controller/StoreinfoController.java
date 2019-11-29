package com.xm.web.xm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xm.web.xm.pojo.Storeorder;
import com.xm.web.xm.services.StoreinfoService;

@RestController
@RequestMapping("StoreinfoController")
public class StoreinfoController {
	@Autowired
	StoreinfoService storeinfoService;
	/**
	 * 分页查询店铺商品
	 * @param stid
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("selectgoodsbystore")
	public List<Map> selectgoodsbystore(int stid,int pagenums){
		return storeinfoService.selectallgoodsofstore(stid,pagenums);
	}
	/**
	 * 查询商店名称
	 * @param stid
	 * @return
	 */
	@RequestMapping("selectstorename")
	public Object selectstorename(int stid){
		return storeinfoService.selectstore(stid);
	}
	/**
	 * 查询商户订单
	 * @param stid
	 * @return
	 */
	@RequestMapping("selectorderbyshop")
	public Object selectorderbyshop(int stid){
		System.out.println(stid);
		List<Map> maps=storeinfoService.selectorderbyshop(stid);
		if(maps.size()!=0){
			List<Map> map=new ArrayList<Map>();
			for (Map ma : maps) {
			if(!storeinfoService.selectbyorderkey((String)ma.get("olid"))){
				map.add(ma);
			}
			}
			return map;
		}
			return false;
	}
	/**
	 * 添加storeorder发货单
	 * @param olid
	 */
	@RequestMapping("insertstoreorder")
	public void insertstoreorder(String olid){
		Storeorder storeorder=new Storeorder();
		storeorder.setOlid(olid);
		storeorder.setOnlin(1);
		storeorder.setTime(new Date());
		storeinfoService.insertstoreorder(storeorder);
	}
	
}
