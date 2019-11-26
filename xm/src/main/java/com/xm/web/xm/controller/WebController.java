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
@RequestMapping(value="/webctl")
public class WebController {
	
	@Autowired
	private AreainfoService service;
	
	
	@RequestMapping(value="getall")
	public Object getAll(){
		return service.getAllAreainfo();
	}
	
	@RequestMapping("getareainfobyaid")
	public Areainfo getAreainfoByAid(Integer aid){
		return service.getAreainfoByAid(aid);
	}
	
	@RequestMapping("doupdate")
	public boolean doUpdate(Areainfo areainfo){
		return service.doupdate(areainfo);
	}
	
	
	
	@RequestMapping("insert")
	public int doinset(Areainfo record){
		if(service.doInsert(record)){
			return getPages();
		}
		return 0;
	}
	
	@RequestMapping("insert2")
	public boolean doinset2(Areainfo record){
	    return service.doInsert(record);
	}
	
	
	@RequestMapping("pages")
	public int getPages(){
		int limit=7;
		return service.getPages(limit);
	}
	
	@RequestMapping("getareabyalevel")
	public Object getareainfoByalevel(Integer alevel){
		return service.getareainfoByAlevel(alevel);
	}
	
	@RequestMapping(value="web")
	public Object ajax(Integer current){
		Integer limit=7;
		Integer pages=service.getPages(limit);
		List<Map>areas=null;
		if(current>0){
			areas=service.getNewLimitAreainfo(current, limit);
		}else{
			areas=service.getNewLimitAreainfo(pages, limit);
		}
		Map re=new HashMap();
		re.put("areas", areas);
		re.put("pages", pages);
		re.put("current", current);
		return re;	
	}
	
	@RequestMapping("del")
	public Boolean deltebyid(Integer aid){
		return service.doDelete(aid);
		 
		
	}
	

}
