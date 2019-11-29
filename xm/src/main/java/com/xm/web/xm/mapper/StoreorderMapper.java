package com.xm.web.xm.mapper;

import com.xm.web.xm.pojo.Storeorder;
import com.xm.web.xm.pojo.StoreorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StoreorderMapper {
    long countByExample(StoreorderExample example);

    int deleteByExample(StoreorderExample example);

    int deleteByPrimaryKey(String olid);

    int insert(Storeorder record);

    int insertSelective(Storeorder record);

    List<Storeorder> selectByExampleWithRowbounds(StoreorderExample example, RowBounds rowBounds);

    List<Storeorder> selectByExample(StoreorderExample example);

    Storeorder selectByPrimaryKey(String olid);

    int updateByExampleSelective(@Param("record") Storeorder record, @Param("example") StoreorderExample example);

    int updateByExample(@Param("record") Storeorder record, @Param("example") StoreorderExample example);

    int updateByPrimaryKeySelective(Storeorder record);

    int updateByPrimaryKey(Storeorder record);
}