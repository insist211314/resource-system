package com.jiuzhi.common.resource.manage.impl;

import com.jiuzhi.common.resource.dao.JobProcessRelationshipDao;
import com.jiuzhi.common.resource.entity.JobProcessRelationship;
import com.jiuzhi.common.resource.entity.JobProcessRelationshipExample;
import com.jiuzhi.common.resource.manage.JobProcessRelationshipManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Service("jobProcessRelationshipManage")
public class JobProcessRelationshipManageImpl implements JobProcessRelationshipManage {

    @Resource
    JobProcessRelationshipDao jobProcessRelationshipDao;

    public Long save(Long jobId, Long processId){
        JobProcessRelationship relationship = new JobProcessRelationship();
        relationship.setJobId(jobId);
        relationship.setProcessId(processId);
        relationship.setCreateTime(new Date());
        relationship.setUpdateTime(new Date());
        jobProcessRelationshipDao.insert(relationship);
        return relationship.getId();
    }

    @Override
    public List<Long> findByProcessId(Long processId) {
        JobProcessRelationshipExample example = new JobProcessRelationshipExample();
        example.createCriteria().andProcessIdEqualTo(processId);
        List<JobProcessRelationship> relationships = jobProcessRelationshipDao.selectByExample(example);

        List<Long> jobs = new ArrayList<>();
        for(JobProcessRelationship relationship : relationships){
            jobs.add(relationship.getJobId());
        }
        return jobs;
    }

    public Boolean isExists(Long jobId, Long processId){
        JobProcessRelationshipExample example = new JobProcessRelationshipExample();
        example.createCriteria().andJobIdEqualTo(jobId).andProcessIdEqualTo(processId);
        if(jobProcessRelationshipDao.countByExample(example)>0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
