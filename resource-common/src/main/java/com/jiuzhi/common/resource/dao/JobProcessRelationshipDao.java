package com.jiuzhi.common.resource.dao;

import com.jiuzhi.common.resource.entity.JobProcessRelationship;
import com.jiuzhi.common.resource.entity.JobProcessRelationshipExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobProcessRelationshipDao {
    int countByExample(JobProcessRelationshipExample example);

    int deleteByExample(JobProcessRelationshipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JobProcessRelationship record);

    int insertSelective(JobProcessRelationship record);

    List<JobProcessRelationship> selectByExample(JobProcessRelationshipExample example);

    JobProcessRelationship selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") JobProcessRelationship record, @Param("example") JobProcessRelationshipExample example);

    int updateByExample(@Param("record") JobProcessRelationship record, @Param("example") JobProcessRelationshipExample example);

    int updateByPrimaryKeySelective(JobProcessRelationship record);

    int updateByPrimaryKey(JobProcessRelationship record);
}