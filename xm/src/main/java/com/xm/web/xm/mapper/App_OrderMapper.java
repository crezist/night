package com.xm.web.xm.mapper;

import com.xm.web.xm.pojo.Areainfo;
import com.xm.web.xm.pojo.AreainfoExample;
import com.xm.web.xm.pojo.Orderinfo;
import com.xm.web.xm.pojo.Orderlist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

public interface App_OrderMapper {
	
	List<Orderlist> makeOrder(List<Integer> ctids );
	
	@Select(value="SELECT SUM(orderlist.gdcount*orderlist.price) FROM orderinfo JOIN orderlist ON orderinfo.ofid=orderlist.ofid WHERE orderinfo.ofid=#{ofid};") 
	Double getsum(String ofid);
	
   
}