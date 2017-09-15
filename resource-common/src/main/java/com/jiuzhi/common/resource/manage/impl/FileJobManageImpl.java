package com.jiuzhi.common.resource.manage.impl;

import com.jiuzhi.common.resource.dao.FileJobDao;
import com.jiuzhi.common.resource.dao.extend.FileJobExtendDao;
import com.jiuzhi.common.resource.entity.FileJob;
import com.jiuzhi.common.resource.entity.FileJobExample;
import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.manage.FileJobManage;
import com.jiuzhi.common.resource.manage.JobProcessRelationshipManage;
import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.model.FileJobBo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
@Service("fileJobManage")
public class FileJobManageImpl implements FileJobManage {

    @Resource
    private FileJobDao fileJobDao;

    @Resource
    private FileJobExtendDao fileJobExtendDao;

    @Resource
    FileDownProcessManage fileDownProcessManage;

    @Resource
    JobProcessRelationshipManage jobProcessRelationshipManage;

    private List<FileJobBo> convert(List<? extends FileJob> list){

        if(list==null)    return null;

        List<FileJobBo> bos = new ArrayList<>();
        for(FileJob entity : list){
            bos.add(convert(entity));
        }
        return bos;
    }

    private FileJobBo convert(FileJob entity){
        if(entity==null)    return null;

        FileJobBo bo = new FileJobBo();
        BeanUtils.copyProperties(entity, bo);

        return bo;
    }

    @Override
    public Long save(FileJobBo bo) {
        bo.setUpdateTime(new Date());
        bo.setCreateTime(new Date());
        bo.setIsDelete(Boolean.FALSE);
        fileJobDao.insert(bo);
        return bo.getId();
    }

    public Long saveOrUpdate(FileJobBo bo){
        if(bo.getId()!=null){
            return update(bo);
        }else{
            return save(bo);
        }
    }

    public Long update(FileJobBo job){
        job.setUpdateTime(new Date());
        fileJobDao.updateByPrimaryKeySelective(job);
        return job.getId();
    }

    public FileJobBo findById(Long id){
        if(id==null)
            return null;
        return convert(fileJobDao.selectByPrimaryKey(id));
    }

    @Transactional
    @Override
    public FileJobBo saveFromResourceDownload(AppBo app, Collection<FileDownProcessBo> accessProcessList, Collection<FileDownProcessBo> downProcessList, Collection<FileDownProcessBo> newProcessList) {

        FileJobBo job = findById(app.getJobId());
        if(job==null) {
            job = new FileJobBo();
        }
        job.setBusinessId(app.getNotifyId());
        job.setBusinessType(app.getNotifyType());
        job.setBusinessApp(app.getAppName());
        job.setTaskCount(accessProcessList.size() + downProcessList.size() + newProcessList.size());
        job.setTaskWaitCount(0);
        job.setNotifyRetryCount(0);
        job.setNotifyStatus((short) 0);
        job.setIsDelete(Boolean.FALSE);
        saveOrUpdate(job);

        List<Long> processList = new ArrayList<>();
        for(FileDownProcessBo bo : accessProcessList) {
            fileDownProcessManage.update(bo);
            if(!jobProcessRelationshipManage.isExists(job.getId(),bo.getId()))
                jobProcessRelationshipManage.save(job.getId(), bo.getId());
            processList.add(bo.getId());
        }
        for(FileDownProcessBo bo : downProcessList) {
            fileDownProcessManage.update(bo);
            if(!jobProcessRelationshipManage.isExists(job.getId(),bo.getId()))
                jobProcessRelationshipManage.save(job.getId(), bo.getId());
            processList.add(bo.getId());
        }
        for(FileDownProcessBo bo : newProcessList) {
            fileDownProcessManage.save(bo);
            if(!jobProcessRelationshipManage.isExists(job.getId(),bo.getId()))
                jobProcessRelationshipManage.save(job.getId(), bo.getId());
            processList.add(bo.getId());
        }
        fileJobExtendDao.updateByTaskWaitCount(job.getId(), processList);

        return job;
    }

    @Override
    public void updateByCompletedDownload(FileDownProcessBo bo, Boolean isSuccessed) {
//        List<Long> jobIds = jobProcessRelationshipManage.findByProcessId(bo.getId());
//        List<FileJobBo> jobs = findByIds(jobIds);
//        for(FileJobBo job : jobs){
//            job.setTaskWaitCount(job.getTaskWaitCount()-1);
//            if(!isSuccessed)
//                job.addFailProcessId(bo.getId());
//            update(job);
//        }
        fileJobExtendDao.updateByCompletedDownload(bo.getId(), !isSuccessed);
    }

    @Override
    public void updateByCompletedNotify(FileJobBo bo, Boolean isSuccessed) {
        if(isSuccessed)
            bo.setNotifyStatus((short)1);
        else {
            if(bo.getNotifyRetryCount()>5)
                bo.setNotifyStatus((short)2);
            bo.setNotifyRetryCount(bo.getNotifyRetryCount() + 1);
        }
        update(bo);
    }

    @Override
    public List<FileJobBo> findByCompletedJob() {
        FileJobExample example = new FileJobExample();
        example.createCriteria()
                .andNotifyStatusEqualTo((short) 0)
                .andTaskWaitCountLessThanOrEqualTo(0);
        return convert(fileJobDao.selectByExampleWithBLOBs(example));
    }

    public List<FileJobBo> findByIds(List<Long> ids){

        if(CollectionUtils.isEmpty(ids))
            return new ArrayList<>(0);

        FileJobExample example = new FileJobExample();
        example.createCriteria().andIdIn(ids);
        return convert(fileJobDao.selectByExample(example));
    }
}
