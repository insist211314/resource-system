package com.jiuzhi.common.resource.dao;

import com.jiuzhi.common.resource.entity.FileDownProcess;
import com.jiuzhi.common.resource.entity.FileDownProcessExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDownProcessDao {
    int countByExample(FileDownProcessExample example);

    int deleteByExample(FileDownProcessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileDownProcess record);

    int insertSelective(FileDownProcess record);

    List<FileDownProcess> selectByExampleWithBLOBs(FileDownProcessExample example);

    List<FileDownProcess> selectByExample(FileDownProcessExample example);

    FileDownProcess selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileDownProcess record, @Param("example") FileDownProcessExample example);

    int updateByExampleWithBLOBs(@Param("record") FileDownProcess record, @Param("example") FileDownProcessExample example);

    int updateByExample(@Param("record") FileDownProcess record, @Param("example") FileDownProcessExample example);

    int updateByPrimaryKeySelective(FileDownProcess record);

    int updateByPrimaryKeyWithBLOBs(FileDownProcess record);

    int updateByPrimaryKey(FileDownProcess record);
}