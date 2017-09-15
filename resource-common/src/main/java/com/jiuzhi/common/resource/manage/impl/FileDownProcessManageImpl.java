package com.jiuzhi.common.resource.manage.impl;

import com.appleframework.model.page.Pagination;
import com.appleframework.orm.mybatis.pagehelper.ISelect;
import com.appleframework.orm.mybatis.pagehelper.Page;
import com.appleframework.orm.mybatis.pagehelper.PageHelper;
import com.jiuzhi.common.resource.dao.FileDownProcessDao;
import com.jiuzhi.common.resource.entity.FileDownProcess;
import com.jiuzhi.common.resource.entity.FileDownProcessExample;
import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@Service
public class FileDownProcessManageImpl implements FileDownProcessManage {

    @Resource
    private FileDownProcessDao fileDownProcessDao;

    private Pagination convert(Pagination pagination){

        if(pagination==null)    return null;

        pagination.setList(convert((List<FileDownProcess>)pagination.getList()));

        return pagination;
    }

    private List<FileDownProcessBo> convert(List<? extends FileDownProcess> list){

        if(list==null)    return null;

        List<FileDownProcessBo> bos = new ArrayList<>();
        for(FileDownProcess entity : list){
            bos.add(convert(entity));
        }
        return bos;
    }

    private FileDownProcessBo convert(FileDownProcess entity){
        if(entity==null)    return null;

        FileDownProcessBo bo = new FileDownProcessBo();
        BeanUtils.copyProperties(entity, bo);

        return bo;
    }

    @Override
    public Long save(FileDownProcess bo) {
        bo.setUpdateTime(new Date());
        bo.setCreateTime(new Date());
        fileDownProcessDao.insert(bo);
        return bo.getId();
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Long update(FileDownProcess bo) {
        bo.setUpdateTime(new Date());
        fileDownProcessDao.updateByPrimaryKeySelective(bo);
        return bo.getId();
    }

    @Override
    public Long saveOrUpdate(FileDownProcess bo) {
        if(bo.getId()==null){
            return save(bo);
        }else{
            return update(bo);
        }
    }

    @Override
    public FileDownProcessBo findById(Long id) {
        return convert(fileDownProcessDao.selectByPrimaryKey(id));
    }

    public FileDownProcessBo findByDownUrlMd5(String md5){
        FileDownProcessExample example = new FileDownProcessExample();
        example.createCriteria().andDownUrlMd5EqualTo(md5);
        return returnOne(fileDownProcessDao.selectByExample(example));
    }

    public FileDownProcessBo findByAccessUrlMd5(String md5){
        FileDownProcessExample example = new FileDownProcessExample();
        example.createCriteria().andAccessUrlMd5EqualTo(md5);
        return returnOne(fileDownProcessDao.selectByExample(example));
    }

    public FileDownProcessBo findByFileMd5(String md5){
        FileDownProcessExample example = new FileDownProcessExample();
        example.createCriteria().andFileMd5EqualTo(md5);
        return returnOne(fileDownProcessDao.selectByExample(example));
    }

    public FileDownProcessBo returnOne(List<FileDownProcess> entitys){
        if(CollectionUtils.isEmpty(entitys)){
            return null;
        }else{
            return convert(entitys.get(0));
        }
    }

    /**
     * 获取需要爬去数据的用户
     * @param size
     * @return
     */
    public List<FileDownProcessBo> queryCrawlingProcess(int size){

        Page<FileDownProcess> page = PageHelper.offsetPage(0, size, false);

        page.doSelectPage(new ISelect() {
            @Override
            public void doSelect() {

                FileDownProcessExample example = new FileDownProcessExample();

                FileDownProcessExample.Criteria criteria = example.createCriteria();
                criteria.andStatusNotIn(Arrays.asList(FileDownProcessBo.StatusEnum.COMPLETED.getVal(), FileDownProcessBo.StatusEnum.EXCEPTION.getVal()));

                example.setOrderByClause("last_down_time asc");

                fileDownProcessDao.selectByExample(example);
            }
        });
        for(FileDownProcess account : page.getResult()){
            account.setLastDownTime(new Date());
            update(account);
        }

        return convert(page.getResult());
    }
}
