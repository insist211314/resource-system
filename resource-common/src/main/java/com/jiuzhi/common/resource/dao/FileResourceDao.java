package com.jiuzhi.common.resource.dao;

import com.jiuzhi.common.resource.entity.FileResource;
import com.jiuzhi.common.resource.entity.FileResourceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileResourceDao {
    int countByExample(FileResourceExample example);

    int deleteByExample(FileResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileResource record);

    int insertSelective(FileResource record);

    List<FileResource> selectByExample(FileResourceExample example);

    FileResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileResource record, @Param("example") FileResourceExample example);

    int updateByExample(@Param("record") FileResource record, @Param("example") FileResourceExample example);

    int updateByPrimaryKeySelective(FileResource record);

    int updateByPrimaryKey(FileResource record);
}