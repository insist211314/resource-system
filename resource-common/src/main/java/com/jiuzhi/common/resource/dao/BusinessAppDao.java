package com.jiuzhi.common.resource.dao;

import com.jiuzhi.common.resource.entity.BusinessApp;
import com.jiuzhi.common.resource.entity.BusinessAppExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAppDao {
    int countByExample(BusinessAppExample example);

    int deleteByExample(BusinessAppExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessApp record);

    int insertSelective(BusinessApp record);

    List<BusinessApp> selectByExample(BusinessAppExample example);

    BusinessApp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessApp record, @Param("example") BusinessAppExample example);

    int updateByExample(@Param("record") BusinessApp record, @Param("example") BusinessAppExample example);

    int updateByPrimaryKeySelective(BusinessApp record);

    int updateByPrimaryKey(BusinessApp record);
}