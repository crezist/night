package com.xm.web.xm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.services.AreainfoService;


@RestController
@RequestMapping(value="/webctl3")
public class WebController3 {
	
	@Autowired
	private AreainfoService service;
	
	@RequestMapping(value="web")
	public Object ajax(Integer current){
		Integer limit=7;
		List<Map> areas=service.getNewLimitAreainfo(current, limit);
		Integer pages=service.getPages(limit);
		Map re=new HashMap();
		re.put("areas", areas);
		re.put("pages", pages);
		re.put("current", current);
		return re;
		
	}
	

}
