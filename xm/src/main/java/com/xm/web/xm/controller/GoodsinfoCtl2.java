package com.xm.web.xm.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.pojo.Typeinfo;
import com.xm.web.xm.services.GoodsinfoService;
import com.xm.web.xm.services.TypeinfoService;
import com.xm.web.xm.services.TypeinfoService;

@RestController
@RequestMapping(value="/Goodsinfoctl2")
public class GoodsinfoCtl2 {
	
	@Autowired
	private GoodsinfoService service;
	
	
	
	
	@RequestMapping("goods")
	public Object goods(Integer current){
		Integer limit=15;
		List<Map> li=service.getGoodsinfo(limit, current);
		Integer pages=service.getPages(limit);
		Integer curren=current;
		List result=new ArrayList();
		Collections.addAll(result, li,pages,curren);
		return result;
	}

}
