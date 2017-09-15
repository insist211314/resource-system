package com.jiuzhi.common.resource.manage.impl;

import com.appleframework.model.page.Pagination;
import com.jiuzhi.common.resource.dao.FileResourceDao;
import com.jiuzhi.common.resource.entity.FileResource;
import com.jiuzhi.common.resource.entity.FileResourceExample;
import com.jiuzhi.common.resource.manage.FileResourceManage;
import com.jiuzhi.common.resource.model.FileResourceBo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@Service("fileResourceManage")
public class FileResourceManageImpl implements FileResourceManage {

    @Resource
    private FileResourceDao fileResourceDao;

    private Pagination convert(Pagination pagination){

        if(pagination==null)    return null;

        pagination.setList(convert((List<FileResource>)pagination.getList()));

        return pagination;
    }

    private List<FileResourceBo> convert(List<? extends FileResource> list){

        if(list==null)    return null;

        List<FileResourceBo> bos = new ArrayList<>();
        for(FileResource entity : list){
            bos.add(convert(entity));
        }
        return bos;
    }

    private FileResourceBo convert(FileResource entity){
        if(entity==null)    return null;

        FileResourceBo bo = new FileResourceBo();
        BeanUtils.copyProperties(entity, bo);

        return bo;
    }

    @Override
    public Long save(FileResource bo) {
        bo.setUpdateTime(new Date());
        bo.setCreateTime(new Date());
        bo.setIsDelete(Boolean.FALSE);
        fileResourceDao.insert(bo);
        return bo.getId();
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Long update(FileResource bo) {
        bo.setUpdateTime(new Date());
        fileResourceDao.updateByPrimaryKeySelective(bo);
        return bo.getId();
    }

    @Override
    public Long saveOrUpdate(FileResource bo) {
        if(bo.getId()==null){
            return save(bo);
        }else{
            return update(bo);
        }
    }

    @Override
    public FileResourceBo findById(Long id) {
        return convert(fileResourceDao.selectByPrimaryKey(id));
    }

    public FileResourceBo findByDownUrlMd5(String md5){
        FileResourceExample example = new FileResourceExample();
        example.createCriteria().andDownUrlMd5EqualTo(md5);
        return returnOne(fileResourceDao.selectByExample(example));
    }

    public FileResourceBo findByFileMd5(String md5){
        FileResourceExample example = new FileResourceExample();
        example.createCriteria().andFileMd5EqualTo(md5);
        return returnOne(fileResourceDao.selectByExample(example));
    }

    public FileResourceBo returnOne(List<FileResource> entitys){
        if(CollectionUtils.isEmpty(entitys)){
            return null;
        }else{
            return convert(entitys.get(0));
        }
    }
}
