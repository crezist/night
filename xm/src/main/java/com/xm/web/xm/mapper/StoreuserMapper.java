package com.xm.web.xm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.xm.web.xm.pojo.Storeuser;
import com.xm.web.xm.pojo.StoreuserExample;


public interface StoreuserMapper {
    long countByExample(StoreuserExample example);

    int deleteByExample(StoreuserExample example);

    int insert(Storeuser record);

    int insertSelective(Storeuser record);

    List<Storeuser> selectByExampleWithRowbounds(StoreuserExample example, RowBounds rowBounds);

    List<Storeuser> selectByExample(StoreuserExample example);

    int updateByExampleSelective(@Param("record") Storeuser record, @Param("example") StoreuserExample example);

    int updateByExample(@Param("record") Storeuser record, @Param("example") StoreuserExample example);
}