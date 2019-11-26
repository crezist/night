package com.xm.web.xm.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.web.xm.mapper.TypeinfoMapper;
import com.xm.web.xm.mapper.TypeinfoMapper;
import com.xm.web.xm.pojo.Typeinfo;
import com.xm.web.xm.pojo.TypeinfoExample;

@Service
public class TypeinfoService {
	
	@Autowired
	private TypeinfoMapper Mapper;
	
	
	//更新数据
	@Transactional
	public boolean doupdate(Typeinfo Typeinfo){
		return Mapper.updateByPrimaryKey(Typeinfo)==1;
	}
	
	
	//删除数据
	@Transactional
	public boolean doDelete(Integer tpid){
		return Mapper.deleteByPrimaryKey(tpid)==1;
	}
	
	
	//插入数据
	@Transactional
	public boolean doInsert(Typeinfo record){
		return Mapper.insert(record)==1;
	}
	
	//获得总页数
	public Integer getPages(Integer limit){
		TypeinfoExample example=new TypeinfoExample();
		int count=(int)Mapper.countByExample(example);
		return (count%limit)==0?(count/limit):(count/limit+1);
	}
	
	//通过主键查找
	public Typeinfo getTypeinfoBytpid(Integer tpid){
		return Mapper.selectByPrimaryKey(tpid);
	}
	
	//分页获取所有Typeinfo
	public List<Typeinfo> getLimitTypeinfo(Integer page,Integer limit){
		TypeinfoExample example=new TypeinfoExample();
		Integer offset=(page-1)*limit;
		RowBounds rowBounds=new RowBounds(offset, limit);
		return Mapper.selectByExampleWithRowbounds(example, rowBounds);
	}
	
	//获取所有Typeinfo
	public List<Typeinfo> getAllTypeinfo(){
		TypeinfoExample example=new TypeinfoExample();
		return Mapper.selectByExample(example);
	}
	

}
