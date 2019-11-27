package com.xm.web.xm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xm.web.xm.mapper.StoreinfoMapper;
import com.xm.web.xm.mapper.StoreinfobyMapper;

@Service
public class StoreinfoService {
	@Autowired
	StoreinfoMapper storeinfoMapper;
	@Autowired
	StoreinfobyMapper storeinfobyMapper;
	
	public List<Map> selectallgoodsofstore(int stid){
		
		return storeinfobyMapper.selectgoodsbystore(stid);
	}
	
	
	
}
