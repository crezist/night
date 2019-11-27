package com.xm.web.xm.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.pojo.Goodsinfo;
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
	public List<Goodsinfo> selectgoodsbystore(int stid,int pagenums){
		return storeinfoService.selectallgoodsofstore(stid,pagenums);
	}
}
