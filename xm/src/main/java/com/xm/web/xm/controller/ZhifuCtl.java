package com.xm.web.xm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.xm.web.xm.services.GoodsinfoService;

@Controller
public class ZhifuCtl {
	
	@Autowired
	GoodsinfoService service;
	
	@RequestMapping("/ali")
	 public void ali(HttpServletResponse response, String ofid) throws IOException, AlipayApiException {
	  service.ali(response,ofid);
	 }
	@RequestMapping("/paydone")
	public String paydone(HttpServletRequest request){
	   service.paydone(request.getParameter("out_trade_no"));
	   return "redirect:http://127.0.0.1:8088";	
	}

}
