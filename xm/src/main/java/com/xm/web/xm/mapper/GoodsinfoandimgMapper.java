package com.xm.web.xm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface GoodsinfoandimgMapper {
	
	List<Map> selectgoodsbystore(Integer stid,Integer begin,Integer last);
}
