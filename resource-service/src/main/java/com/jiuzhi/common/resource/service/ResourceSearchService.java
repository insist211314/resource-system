package com.jiuzhi.common.resource.service;

import com.jiuzhi.common.resource.model.FileResourceBo;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface ResourceSearchService {

    FileResourceBo findByUrl(String url);


}
