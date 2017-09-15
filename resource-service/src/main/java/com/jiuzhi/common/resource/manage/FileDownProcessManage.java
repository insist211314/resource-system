package com.jiuzhi.common.resource.manage;

import com.jiuzhi.common.resource.entity.FileDownProcess;
import com.jiuzhi.common.resource.entity.FileResource;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.model.FileResourceBo;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface FileDownProcessManage {


    Long save(FileDownProcess bo);

    Long delete(Long id);

    Long update(FileDownProcess bo);

    Long saveOrUpdate(FileDownProcess bo);

    FileDownProcessBo findById(Long id);

    FileDownProcessBo findByDownUrlMd5(String md5);

    FileDownProcessBo findByAccessUrlMd5(String md5);

    FileDownProcessBo findByFileMd5(String md5);

    List<FileDownProcessBo> queryCrawlingProcess(int num);


}
