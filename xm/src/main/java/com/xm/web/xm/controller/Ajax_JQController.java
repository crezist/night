package com.xm.web.xm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.pojo.Areainfo;


@RestController
@RequestMapping(value="/ajaxjqctl")
public class Ajax_JQController {
	
	@RequestMapping(value="ajax")
	public Object ajax(Areainfo areainfo){
		System.out.println(areainfo);
		Map m1=new HashMap();
		m1.put("stname", "小明");
		m1.put("stage", 10);
		
		Map m2=new HashMap();
		m2.put("stname", "小猴");
		m2.put("stage", 20);	
		
		Map m3=new HashMap();
		m3.put("stname", "小北");
		m3.put("stage", 16);	
		
		List li=new ArrayList();
		Collections.addAll(li, m1,m2,m3);
		return li;
	}
}
