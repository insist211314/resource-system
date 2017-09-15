package com.jiuzhi.common.resource.manage;

import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.BusinessAppBo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface BusinessAppManage {

    public void add(AppBo bo);

    public Long save(BusinessAppBo bo);

    public List<BusinessAppBo> findByAppName(String appName);

    public BusinessAppBo findByAppNameAndIpAndJmxPort(String appName, String ip, Integer jmxPort);
}
