package com.jiuzhi.common.resource.dao;

import com.jiuzhi.common.resource.entity.FileJob;
import com.jiuzhi.common.resource.entity.FileJobExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileJobDao {
    int countByExample(FileJobExample example);

    int deleteByExample(FileJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileJob record);

    int insertSelective(FileJob record);

    List<FileJob> selectByExampleWithBLOBs(FileJobExample example);

    List<FileJob> selectByExample(FileJobExample example);

    FileJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileJob record, @Param("example") FileJobExample example);

    int updateByExampleWithBLOBs(@Param("record") FileJob record, @Param("example") FileJobExample example);

    int updateByExample(@Param("record") FileJob record, @Param("example") FileJobExample example);

    int updateByPrimaryKeySelective(FileJob record);

    int updateByPrimaryKeyWithBLOBs(FileJob record);

    int updateByPrimaryKey(FileJob record);
}