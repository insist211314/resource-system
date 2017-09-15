package com.jiuzhi.common.resource.service;

import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.ResourceDownloadBo;
import com.jiuzhi.common.resource.model.DownloadResponseBo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface ResourceDownloadService {

    DownloadResponseBo downResource(AppBo appBo, ResourceDownloadBo... downloadBos);

    DownloadResponseBo downResource(AppBo appBo, List<ResourceDownloadBo> downloadList);

}
