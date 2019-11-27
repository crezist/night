package com.xm.web.xm.test;



import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.OrderedMap;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xm.web.xm.controller.GoodsinfoCtl;
import com.xm.web.xm.mapper.App_OrderMapper;
import com.xm.web.xm.pojo.Goodsinfo;
import com.xm.web.xm.pojo.Orderlist;
import com.xm.web.xm.pojo.User;
import com.xm.web.xm.services.GoodsinfoService;
import com.xm.web.xm.services.LiDongxuService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackages="com.xm.web.xm")
@MapperScan(basePackages="com.xm.web.xm.mapper")
public class TestUnit {

	
	@Autowired
	App_OrderMapper  ordermapper;
	@Autowired
	LiDongxuService lidongxuService;
	
	@Test
	public void test3(){
		List<Map> tt=lidongxuService.selectAllcollection(1);
		for (int i = 0; i < tt.size(); i++) {
			System.out.println(tt.get(i));
		}
	}
	


}
