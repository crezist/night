package com.xm.web.xm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.Storeinfo;
import com.xm.web.xm.services.Storservice;


@RestController
@RequestMapping("StoreuserController")
public class StoreuserController {
	@Autowired
	Storservice storservice;
	@RequestMapping("doonestore")
	public Object doonestore(Storeinfo storeinfo/*,HttpSession session*/){
//		User user=(User)session.getAttribute("user");
		System.out.println(storeinfo.getStname()+storeinfo.getStaddress()+storeinfo.getStnum()+storeinfo.getStareaid());
//		storservice.insertuserbystor(user.getUserid(),storeinfo);
		return true;
	}
	/**
	 * 查询所有地区
	 * @return
	 */
	@RequestMapping("selectareainfo")
	public List<Areainfo> selectareainfo(){
		return storservice.selectallareainfo();
	}
	
}
