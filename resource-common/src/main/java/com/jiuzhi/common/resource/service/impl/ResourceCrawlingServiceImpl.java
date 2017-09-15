package com.jiuzhi.common.resource.service.impl;

import java.io.File;

import javax.annotation.Resource;

import com.jiuzhi.common.resource.manage.FileJobManage;
import com.jiuzhi.common.resource.manage.JobProcessRelationshipManage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.appleframework.config.core.PropertyConfigurer;
import com.jiuzhi.common.resource.constant.FileType;
import com.jiuzhi.common.resource.constants.PropertiesKeyConstant;
import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.manage.FileResourceManage;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.service.ResourceCrawlingService;
import com.jiuzhi.common.resource.utils.FileDownload;
import com.jiuzhi.common.resource.utils.FileUtil;
import com.jiuzhi.common.tools.upload.QiniuMediaCommon;

/**
 * Created by Administrator on 2016/11/12.
 */
@Service("resourceCrawlingService")
@Lazy(false)
public class ResourceCrawlingServiceImpl implements ResourceCrawlingService {

    @Resource
    private FileDownProcessManage fileDownProcessManage;

    @Resource
    private FileJobManage fileJobManage;

    @Resource
    private FileResourceManage fileResourceManage;

    public void crawlingResource(FileDownProcessBo bo){

        try {
            if (!bo.existsDiskFile()) {
                downloadFile(bo);
            }
            uploadFile(bo);
            fileResourceManage.save(bo.adapterFileResource());
        }catch(Exception e){
            crawlingException(bo, e);
            throw new RuntimeException(e);
        }
    }

    private void downloadFile(FileDownProcessBo bo){
        String diskUrl = bo.getIsProxy()?FileDownload.downloadProxy2(bo.getDownUrl(), bo.getExtension()):FileDownload.download2(bo.getDownUrl(), bo.getExtension());
        bo.setFileDiskUrl(diskUrl);
        bo.setExtension(FileUtil.getFileExtension(diskUrl));
        bo.setStatus(FileDownProcessBo.StatusEnum.DOWNLOADED.getVal());
        fileDownProcessManage.update(bo);
    }

    private void uploadFile(FileDownProcessBo bo){
        QiniuMediaCommon upload = new QiniuMediaCommon();
        if(bo.getTypeEnum()==FileType.MEDIA)
            upload.uploadVideo(bo.getAccessUrl(), new File(bo.getFileDiskUrl()));
        else if(bo.getTypeEnum()==FileType.IMAGE)
            upload.uploadImage(bo.getAccessUrl(), new File(bo.getFileDiskUrl()));
        else{
            throw new RuntimeException("文件类型为空! file down process id="+bo.getId());
        }
        bo.setStatusEnum(FileDownProcessBo.StatusEnum.COMPLETED);
        fileDownProcessManage.update(bo);
        fileJobManage.updateByCompletedDownload(bo, Boolean.TRUE);
    }

    private void crawlingException(FileDownProcessBo bo, Exception e){
        bo.setRetryCount(bo.getRetryCount()+1);
        if(bo.getRetryCount()>= PropertyConfigurer.getInteger(PropertiesKeyConstant.TASK_CRAWLING_RETRY_COUNT, 5)) {
            bo.setStatusEnum(FileDownProcessBo.StatusEnum.EXCEPTION);
            fileJobManage.updateByCompletedDownload(bo, Boolean.FALSE);
        }
        bo.setExcInfo(e.getMessage());
        fileDownProcessManage.update(bo);
    }
    
}
