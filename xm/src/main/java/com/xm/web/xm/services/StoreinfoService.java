package com.xm.web.xm.services;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.web.xm.mapper.GoodsinfoMapper;
import com.xm.web.xm.mapper.StoreinfoMapper;
import com.xm.web.xm.mapper.StoreinfobyMapper;
import com.xm.web.xm.pojo.Goodsinfo;
import com.xm.web.xm.pojo.GoodsinfoExample;

@Service
public class StoreinfoService {
	@Autowired
	StoreinfoMapper storeinfoMapper;
	@Autowired
	StoreinfobyMapper storeinfobyMapper;
	@Autowired
	GoodsinfoMapper goodsinfoMapper;
	/**
	 * 查询本店所有商品
	 * @param stid
	 * @return
	 */
	public List<Goodsinfo> selectallgoodsofstore(int stid,int pagenums){
		GoodsinfoExample example=new GoodsinfoExample();
		example.createCriteria().andStidEqualTo(stid);
		int  file=(pagenums-1)*30;
		RowBounds rowBounds=new RowBounds(file,30);
		return goodsinfoMapper.selectByExampleWithRowbounds(example, rowBounds);
		
	}
}
