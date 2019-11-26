package com.xm.web.xm.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping(value="/ajaxctl")
public class AjaxController {
	
	@RequestMapping(value="ajax")
	//@ResponseBody
	public String ajax(String aname,Integer aid){
		System.out.println("参数是"+aname+"_"+aid);
		return "服务器返回值:"+UUID.randomUUID();
	}
}
