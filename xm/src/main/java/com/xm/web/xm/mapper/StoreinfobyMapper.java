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
}
