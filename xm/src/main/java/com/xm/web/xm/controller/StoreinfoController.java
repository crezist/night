package com.xm.web.xm.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.services.StoreinfoService;

@RestController
@RequestMapping("StoreinfoController")
public class StoreinfoController {
	@Autowired
	StoreinfoService storeinfoService;
	
	public List<Map> selectgoodsbystore(int stid){
		return storeinfoService.selectallgoodsofstore(stid);
	}
}
