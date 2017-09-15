package com.jiuzhi.common.resource.manage;

import com.jiuzhi.common.resource.entity.FileResource;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.model.FileResourceBo;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface FileResourceManage {

    Long save(FileResource bo);

    Long delete(Long id);

    Long update(FileResource bo);

    Long saveOrUpdate(FileResource bo);

    FileResourceBo findById(Long id);

    FileResourceBo findByDownUrlMd5(String md5);

    FileResourceBo findByFileMd5(String md5);
}
