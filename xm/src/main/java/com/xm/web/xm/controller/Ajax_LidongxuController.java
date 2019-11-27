package com.xm.web.xm.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.web.xm.pojo.GoodscollectionKey;
import com.xm.web.xm.pojo.User;
import com.xm.web.xm.services.LiDongxuService;

//@Controller
@RestController
@RequestMapping(value="/Ajax_LidongxuController")
public class Ajax_LidongxuController {
	
@Autowired
LiDongxuService lidongxuService;	


@RequestMapping(value="/selectAllcollection")
public Object selectAllcollection(Integer userid){
	List<Map> cont=lidongxuService.selectAllcollection(userid);
	return cont;
}




@RequestMapping(value="/selectGoodscollection")
public boolean selectGoodscollection(Integer gdid,Integer userid){
	return lidongxuService.selectGoodscollection(gdid, userid);
}

	
	
@RequestMapping(value="insertgoodskey")
public boolean insertgoodskey(GoodscollectionKey goodskey){
	return lidongxuService.insertgoodscollectionMapper(goodskey);
}

@RequestMapping(value="delectgoodskey")
public boolean delectgoodskey(Integer gdid,Integer userid){
	return lidongxuService.delectgoodscollec(gdid, userid);
}
	
}
