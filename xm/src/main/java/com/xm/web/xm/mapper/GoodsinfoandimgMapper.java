package com.xm.web.xm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
public interface GoodsinfoandimgMapper {
	
	List<Map> selectgoodsbystore(Integer stid,RowBounds bounds);
}
