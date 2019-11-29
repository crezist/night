package com.xm.web.xm.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.Storeinfo;
import com.xm.web.xm.pojo.Storeuser;
import com.xm.web.xm.pojo.User;
import com.xm.web.xm.services.Storservice;


@RestController
@RequestMapping("StoreuserController")
public class StoreuserController {
	@Autowired
	Storservice storservice;
	/**
	 * 添加商户
	 * @param storeinfo
	 * @param session
	 * @return
	 */
	@RequestMapping("doonestore")
	public Object doonestore(Storeinfo storeinfo,HttpSession session){
		User user=(User)session.getAttribute("user");
		return storservice.insertuserbystor(user.getUserid(),storeinfo);
	}
	/**
	 * 查询所有地区
	 * @return
	 */
	@RequestMapping("selectareainfo")
	public List<Areainfo> selectareainfo(){
		return storservice.selectallareainfo();
	}
	/**
	 * 查询是否存在该商户
	 * @param session
	 * @return
	 */
	@RequestMapping("userisstoreuser")
	public Object userisstoreuser(HttpSession session){
		User user=(User)session.getAttribute("user");
		List<Storeuser> stuser=storservice.selectstoreuserishave(user.getUserid());
		if(stuser.size()==0){
			return false;
		}else{
			return stuser.get(0).getStid();
		}
//		return null;
	}
}
