package com.xm.web.xm.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xm.web.xm.pojo.Typeinfo;
import com.xm.web.xm.services.TypeinfoService;
import com.xm.web.xm.services.TypeinfoService;

@Controller
@RequestMapping(value="/Typeinfoctl")
public class TypeinfoCtl {
	
	@Autowired
	private TypeinfoService service;
	
	
	@RequestMapping(value="del")
    public String dodel(Integer tpid,Integer current,ModelMap map){
		service.doDelete(tpid);
		Integer limit=6;
		if(service.getLimitTypeinfo(current, limit).size()==0){
			return  this.getLimitTypeinfo(current-1,map );
		}else{
			return this.getLimitTypeinfo(current,map );
		}
	}
	
	@RequestMapping(value="doupdate")
	public String doupdate(Typeinfo Typeinfo,Integer current,ModelMap map){
		service.doupdate(Typeinfo);
		return getLimitTypeinfo(current, map);
		
	}
	
	@RequestMapping(value="readyupdate")
	public String readyUpdate(Integer tpid,Integer current,ModelMap map){
		map.put("Types", service.getAllTypeinfo());
		map.put("Type",service.getTypeinfoBytpid(tpid));
		map.put("current", current);
		return "/readyupdatetype.jsp";
	}
	
	@RequestMapping(value="doinsert")
	public String doInsert(Typeinfo Typeinfo,ModelMap map){
		service.doInsert(Typeinfo);
		Integer limit=6;
		Integer page=service.getPages(limit);
		return getLimitTypeinfo(page, map);
	}
	
	@RequestMapping(value="readyinsert")
	public String readyInsert(ModelMap map){
		map.put("Type", service.getAllTypeinfo());
		return "/readyinserttype.jsp";
	}
	
	@RequestMapping(value="getlimitTypeinfo")
	public String getLimitTypeinfo(Integer page,ModelMap map){
		Integer limit=6;
		Integer pagee=1;
		if(page!=null){
			pagee=page;
		}
		map.put("Typeinfo", service.getLimitTypeinfo(pagee, limit));
		map.put("current", pagee);
		map.put("pages", service.getPages(limit));
	    return "/Typeinfo.jsp";
		
	}

}
