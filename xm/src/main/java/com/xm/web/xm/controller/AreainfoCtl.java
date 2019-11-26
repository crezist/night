package com.xm.web.xm.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.services.AreainfoService;

@Controller
@RequestMapping(value="/areainfoctl")
public class AreainfoCtl {
	
	@Autowired
	private AreainfoService service;
	
	
	@RequestMapping(value="del")
    public String dodel(Integer aid,Integer current,ModelMap map){
		service.doDelete(aid);
		Integer limit=6;
		if(service.getLimitAreainfo(current, limit).size()==0){
			return  this.getLimitAreainfo(current-1,map );
		}else{
			return this.getLimitAreainfo(current,map );
		}
	}
	
	@RequestMapping(value="doupdate")
	public String doupdate(Areainfo areainfo,Integer current,ModelMap map){
		service.doupdate(areainfo);
		return getLimitAreainfo(current, map);
		
	}
	
	@RequestMapping(value="readyupdate")
	public String readyUpdate(Integer aid,Integer current,ModelMap map){
		map.put("areas", service.getAllAreainfo());
		map.put("area",service.getAreainfoByAid(aid));
		map.put("current", current);
		return "/readyupdate.jsp";
	}
	
	@RequestMapping(value="doinsert")
	public String doInsert(Areainfo areainfo,ModelMap map){
		service.doInsert(areainfo);
		Integer limit=6;
		Integer page=service.getPages(limit);
		return getLimitAreainfo(page, map);
	}
	
	@RequestMapping(value="readyinsert")
	public String readyInsert(ModelMap map){
		map.put("area", service.getAllAreainfo());
		return "/readyinsert.jsp";
	}
	
	@RequestMapping(value="getlimitareainfo")
	public String getLimitAreainfo(Integer page,ModelMap map){
		Integer limit=6;
		Integer pagee=1;
		if(page!=null){
			pagee=page;
		}
		map.put("areainfo", service.getLimitAreainfo(pagee, limit));
		map.put("current", pagee);
		map.put("pages", service.getPages(limit));
	    return "/areainfo.jsp";
		
	}

}
