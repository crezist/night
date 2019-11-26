package com.xm.web.xm.mapper;


import com.xm.web.xm.pojo.GoodsinfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

public interface App_GoodsinfoMapper {
	
	String selectdetail="SELECT goods.gdid,goods.gdname,image.gimgurl,gprice.price FROM goodsinfo goods JOIN goodsimage image ON goods.gdid=image.gdid JOIN goodsprice gprice ON goods.gdid=gprice.gdid WHERE gprice.utid=1 AND image.gimgtype=1 AND goods.gdid=#{gdid}";
	
	List<Map> getGoodsinfoWithRowbounds(GoodsinfoExample example,RowBounds rowBounds);
	
	@Select("<script>"+selectdetail+"</script>")
	Map getDetail(Integer gdid);
   
}