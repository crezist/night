package com.xm.web.xm.services;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.web.xm.mapper.App_AreainfoMapper;
import com.xm.web.xm.mapper.AreainfoMapper;
import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.AreainfoExample;



@Service
public class AreainfoService {
	
	
	@Autowired
	private AreainfoMapper Mapper;
	
	@Autowired
	private App_AreainfoMapper app_AreainfoMapper;
	
	
    
	
	//分页获取新的Areainfo
	public List<Map> getNewLimitAreainfo(Integer page,Integer limit){
		AreainfoExample example=new AreainfoExample();
		Integer offset=(page-1)*limit;
		RowBounds rowBounds=new RowBounds(offset, limit);
		return app_AreainfoMapper.getNewAreainfoWithRowbounds(example, rowBounds);
	}
	

	
	//更新数据
	@Transactional
	public boolean doupdate(Areainfo areainfo){
		return Mapper.updateByPrimaryKey(areainfo)==1;
	}
	
	
	//删除数据
	@Transactional
	public boolean doDelete(Integer aid){
		return Mapper.deleteByPrimaryKey(aid)==1;
	}
	
	
	//插入数据
	@Transactional
	public boolean doInsert(Areainfo record){
		return Mapper.insert(record)==1;
	}
	
	//获得总页数
	public Integer getPages(Integer limit){
		AreainfoExample example=new AreainfoExample();
		int count=(int)Mapper.countByExample(example);
		return (count%limit)==0?(count/limit):(count/limit+1);
	}
	
	//通过主键查找
	public Areainfo getAreainfoByAid(Integer aid){
		return Mapper.selectByPrimaryKey(aid);
	}
	
	//按照alevel的查找
	public List<Areainfo> getareainfoByAlevel(Integer alevel){
		AreainfoExample example=new AreainfoExample();
		example.createCriteria().andAlevelEqualTo(alevel);
		return Mapper.selectByExample(example);
	}
	
	
	//分页获取所有Areainfo
	public List<Areainfo> getLimitAreainfo(Integer page,Integer limit){
		AreainfoExample example=new AreainfoExample();
		Integer offset=(page-1)*limit;
		RowBounds rowBounds=new RowBounds(offset, limit);
		return Mapper.selectByExampleWithRowbounds(example, rowBounds);
	}
	
	//获取所有Areainfo
	public List<Areainfo> getAllAreainfo(){
		AreainfoExample example=new AreainfoExample();
		return Mapper.selectByExample(example);
	}
	

}
