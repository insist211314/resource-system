package com.jiuzhi.common.resource.provider.service.impl;

import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.manage.FileResourceManage;
import com.jiuzhi.common.resource.model.FileResourceBo;
import com.jiuzhi.common.resource.service.ResourceSearchService;
import com.jiuzhi.common.resource.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/11.
 */
@Service("resourceSearchService")
public class ResourceSearchServiceImpl implements ResourceSearchService {

    @Resource
    FileResourceManage fileResourceManage;

    @Resource
    FileDownProcessManage fileDownProcessManage;

    @Override
    public FileResourceBo findByUrl(String url) {
        String urlMd5 = Md5Util.md5DigestByString(url);
        return fileResourceManage.findByDownUrlMd5(url);
    }



}
