package com.xm.web.xm.mapper;

import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.AreainfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface App_BrowsercartMapper {
	
    List<Map> getcart(Integer userid);	
  
}