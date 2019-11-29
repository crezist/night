package com.xm.web.xm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface StoreinfobyMapper {
	@Select("<script>SELECT goods.`gdid`,goods.`gdname`,image.`gimgurl`"
			+ "FROM storeinfo store JOIN goodsinfo goods ON store.`stid`=goods.`stid`"
			+ "JOIN goodsimage image ON goods.`gdid`=image.`gdid`"
			+ "WHERE store.stid=#{stid} AND image.`gimgtype`=1</script>")
	List<Map> selectgoodsbystore(Integer stid);
	@Select("<script>SELECT orli.`olid`,gofo.`gdname`,orli.`price`,orli.`gsid`,orfo.`address`,orfo.`recipient`,orfo.`contactnumber`,orfo.`ofdate` FROM orderlist orli "
			+ "left JOIN orderinfo orfo ON orli.`ofid`=orfo.`ofid` "
			+ "JOIN goodsinfo gofo ON gofo.`gdid`=orli.`gdid` "
			+ "JOIN storeinfo stfo ON stfo.`stid`=gofo.`stid` "
			+ "JOIN goodssize size ON size.`gsid`=orli.`gsid` "
			+ "WHERE orfo.`ofstate`=2 AND stfo.`stid`=#{stid} "
			+ "ORDER BY orfo.ofdate ASC</script>")
	List<Map> selectorderbyshop(Integer stid);
	
}
