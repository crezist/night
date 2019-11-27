package com.xm.web.xm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.web.xm.mapper.App_LiDongxuMapper;
import com.xm.web.xm.mapper.GoodscollectionMapper;
import com.xm.web.xm.pojo.GoodscollectionExample;
import com.xm.web.xm.pojo.GoodscollectionKey;

@Service
public class LiDongxuService {
	
@Autowired
GoodscollectionMapper goodscollectionMapper;

@Autowired
App_LiDongxuMapper app_LiDongxuMapper;



public List<Map> selectAllcollection(Integer userid){
	List<Map> conllection=app_LiDongxuMapper.getgoodscollection(userid);
	return conllection;
}



public boolean selectGoodscollection(Integer gdid,Integer userid){
	GoodscollectionExample example=new GoodscollectionExample();
	example.createCriteria().andGdidEqualTo(gdid).andUseridEqualTo(userid);
	List<GoodscollectionKey> gg=goodscollectionMapper.selectByExample(example);
	return gg.size()==0;
}



	
@Transactional
public boolean insertgoodscollectionMapper(GoodscollectionKey goodskey){
	return goodscollectionMapper.insert(goodskey)==1;
}
	
@Transactional
public boolean delectgoodscollec(Integer gdid,Integer userid){
	GoodscollectionExample example=new GoodscollectionExample();
	example.createCriteria().andGdidEqualTo(gdid).andUseridEqualTo(userid);
	return goodscollectionMapper.deleteByExample(example)==1;
}


}
