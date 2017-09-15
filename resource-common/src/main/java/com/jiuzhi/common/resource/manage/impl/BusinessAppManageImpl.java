package com.jiuzhi.common.resource.manage.impl;

import com.jiuzhi.common.resource.dao.BusinessAppDao;
import com.jiuzhi.common.resource.entity.BusinessApp;
import com.jiuzhi.common.resource.entity.BusinessAppExample;
import com.jiuzhi.common.resource.manage.BusinessAppManage;
import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.BusinessAppBo;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service("businessAppManage")
public class BusinessAppManageImpl implements BusinessAppManage {

    @Resource
    BusinessAppDao businessAppDao;

    private List<BusinessAppBo> convert(List<? extends BusinessApp> list){

        if(list==null)    return null;

        List<BusinessAppBo> bos = new ArrayList<>();
        for(BusinessApp entity : list){
            bos.add(convert(entity));
        }
        return bos;
    }

    private BusinessAppBo convert(BusinessApp entity){
        if(entity==null)    return null;

        BusinessAppBo bo = new BusinessAppBo();
        BeanUtils.copyProperties(entity, bo);

        return bo;
    }

    @Override
    @Async
    @Transactional
    public void add(AppBo bo) {
        BusinessAppBo appBo = findByAppNameAndIpAndJmxPort(bo.getAppName(), bo.getIp(), bo.getJmxPort());
        if(appBo!=null){
            if(appBo.getIsDelete()) {
                appBo.setIsDelete(Boolean.FALSE);
                update(appBo);
            }
        }else{
            appBo = new BusinessAppBo();
            appBo.setIp(bo.getIp());
            appBo.setAppName(bo.getAppName());
            appBo.setJmxPort(bo.getJmxPort());
            save(appBo);
        }
    }

    @Override
    public Long save(BusinessAppBo bo) {
        bo.setUpdateTime(new Date());
        bo.setCreateTime(new Date());
        bo.setIsDelete(Boolean.FALSE);
        businessAppDao.insert(bo);
        return bo.getId();
    }

    @Override
    public List<BusinessAppBo> findByAppName(String appName) {
        BusinessAppExample example = new BusinessAppExample();
        example.createCriteria()
                .andAppNameEqualTo(appName);
        example.setOrderByClause("update_time desc");
        return convert(businessAppDao.selectByExample(example));
    }

    public Long update(BusinessAppBo bo){
        bo.setUpdateTime(new Date());
        businessAppDao.updateByPrimaryKey(bo);
        return bo.getId();
    }

    public BusinessAppBo findByAppNameAndIpAndJmxPort(String appName, String ip, Integer jmxPort){
        BusinessAppExample example = new BusinessAppExample();
        example.createCriteria()
                .andAppNameEqualTo(appName)
                .andIpEqualTo(ip)
                .andJmxPortEqualTo(jmxPort);
        List<BusinessApp> bos = businessAppDao.selectByExample(example);
        if(CollectionUtils.isEmpty(bos))
            return null;
        return convert(bos.get(0));
    }
}
