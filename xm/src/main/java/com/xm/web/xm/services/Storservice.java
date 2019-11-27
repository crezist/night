package com.xm.web.xm.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xm.web.xm.mapper.AreainfoMapper;
import com.xm.web.xm.mapper.StoreinfoMapper;
import com.xm.web.xm.mapper.StoreuserMapper;
import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.AreainfoExample;
import com.xm.web.xm.pojo.Storeinfo;
import com.xm.web.xm.pojo.StoreinfoExample;
import com.xm.web.xm.pojo.Storeuser;
@Service
public class Storservice {
	@Autowired
	StoreuserMapper storeuserMapper;
	@Autowired
	StoreinfoMapper storeinfoMapper;
	@Autowired
	AreainfoMapper areainfoMapper;
	/**
	 * 查询所有地区信息
	 * @return
	 */
	public List<Areainfo> selectallareainfo(){
		AreainfoExample example=new AreainfoExample();
		List<Areainfo> area= areainfoMapper.selectByExample(example);
		return area;
	} 
	/**
	 * 添加商户信息
	 * @param userid
	 * @param storeinfo
	 */
	@Transactional
	public void insertuserbystor(int userid,Storeinfo storeinfo){
	
		storeinfoMapper.insert(storeinfo);
		StoreinfoExample example=new StoreinfoExample();
		example.createCriteria().andStnameEqualTo(storeinfo.getStname());
		Storeinfo sto=storeinfoMapper.selectByExample(example).get(0);
		Storeuser record=new Storeuser();
		record.setUserid(userid);
		record.setStid(sto.getStid());
		storeuserMapper.insert(record);
	}
}
