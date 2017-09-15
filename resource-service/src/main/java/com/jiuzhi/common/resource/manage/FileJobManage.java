package com.jiuzhi.common.resource.manage;

import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.model.FileJobBo;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public interface FileJobManage {

    public Long save(FileJobBo bo);

    public FileJobBo saveFromResourceDownload(AppBo app, Collection<FileDownProcessBo> accessProcessList, Collection<FileDownProcessBo> downProcessList, Collection<FileDownProcessBo> newProcessList);

    public void updateByCompletedDownload(FileDownProcessBo bo, Boolean isSuccesssed);

    public void updateByCompletedNotify(FileJobBo bo, Boolean isSuccessed);

    public List<FileJobBo> findByCompletedJob();

    public FileJobBo findById(Long id);

    public Long saveOrUpdate(FileJobBo bo);
}
