package com.xm.web.xm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;



public interface App_GetOrderMapper {
	
String sql="SELECT info.ofid,info.ofdate,info.ofstate,ginfo.gdname,list.price,list.gdcount,size.gstext,image.gimgurl FROM orderinfo info JOIN orderlist LIST ON info.ofid=list.ofid JOIN USER ON user.userid=info.userid JOIN goodsinfo ginfo ON ginfo.gdid=list.gdid JOIN goodssize size ON list.gsid=size.gsid JOIN goodsimage image ON image.gdid=list.gdid  WHERE user.userid=#{userid};";
    @Select("<script>"+sql+"</script>")
    List<Map> getorder(Integer userid);
}
