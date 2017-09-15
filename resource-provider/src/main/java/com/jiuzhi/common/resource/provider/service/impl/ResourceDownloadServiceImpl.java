package com.jiuzhi.common.resource.provider.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.appleframework.config.core.PropertyConfigurer;
import com.jiuzhi.common.resource.constants.PropertiesKeyConstant;
import com.jiuzhi.common.resource.manage.BusinessAppManage;
import com.jiuzhi.common.resource.manage.FileJobManage;
import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.FileJobBo;
import com.jiuzhi.common.resource.model.ResourceDownloadBo;
import com.jiuzhi.common.resource.model.DownloadResponseBo;
import com.jiuzhi.common.resource.utils.FileUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.manage.FileResourceManage;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.service.ResourceDownloadService;
import com.jiuzhi.common.resource.utils.FileDownload;
import com.jiuzhi.common.resource.utils.Md5Util;

/**
 * Created by Administrator on 2016/11/12.
 */
@Service("resourceDownloadService")
public class ResourceDownloadServiceImpl implements ResourceDownloadService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceDownloadServiceImpl.class);

    @Resource
    FileResourceManage fileResourceManage;

    @Resource
    FileDownProcessManage fileDownProcessManage;

    @Resource
    FileJobManage fileJobManage;

    @Resource
    BusinessAppManage businessAppManage;

    static String[] extensions = {"jpg", "jpge", "gif", "mp4", "png"};
    public static String getFileExtension(String url){
        String e = FileUtil.getFileExtension(url);
        for(String extension : extensions){
            if(extension.equals(e))
                return e;
        }
        e = FileDownload.getFileExtension(url);
        return e;
    }

    @Override
    public DownloadResponseBo downResource(AppBo appBo, ResourceDownloadBo... downloadBos) {
        return downResource(appBo, Arrays.asList(downloadBos));
    }

    @Override
    public DownloadResponseBo downResource(AppBo appBo, List<ResourceDownloadBo> downloadList) {
        DownloadResponseBo response = new DownloadResponseBo();
        if(CollectionUtils.isEmpty(downloadList))
            return response;

        Map<String, FileDownProcessBo> accessProcesMap = getProcessByAccessUrl(downloadList);
        Map<String, FileDownProcessBo> downProcessMap = getProcessByDownUrl(accessProcesMap, downloadList);
        Map<String, FileDownProcessBo> newProcessMap = getNewProcess(appBo, accessProcesMap, downProcessMap, downloadList);

        FileJobBo jobBo = fileJobManage.saveFromResourceDownload(appBo, accessProcesMap.values(), downProcessMap.values(), newProcessMap.values());
        try{
            businessAppManage.add(appBo);
        }catch(Exception e){
    //            logger.error("添加BusinessApp失败!", e);
        }


        response.setJobId(jobBo.getId());

        for(ResourceDownloadBo bo : downloadList){

            FileDownProcessBo processBo = newProcessMap.get(bo.getUrl());
            if(processBo==null)
                processBo = downProcessMap.get(bo.getUrl());
            if(processBo==null)
                processBo = accessProcesMap.get(bo.getAccessUrl());
            response.addAccessUrl(bo.getKey(), processBo.getAccessUrl());
        }

        return response;
    }

    private Map<String, FileDownProcessBo> getProcessByAccessUrl(List<ResourceDownloadBo>  downloadList){

        Map<String, FileDownProcessBo> accessProcessMap = new HashMap<>();

        for(ResourceDownloadBo bo : downloadList){
            if(!StringUtils.hasText(bo.getAccessUrl()))
                continue;

            String accessUrlMd5 = Md5Util.md5DigestByString(bo.getAccessUrl());
            FileDownProcessBo process = fileDownProcessManage.findByAccessUrlMd5(accessUrlMd5);
            if(process!=null) {
                if(process.getStatusEnum() != FileDownProcessBo.StatusEnum.COMPLETED) {
                    process.setDownUrl(bo.getUrl());
                    process.setRetryCount(0);
                    process.setStatusEnum(FileDownProcessBo.StatusEnum.STARTED);
                }
                accessProcessMap.put(bo.getAccessUrl(), process);
            }
        }

        return accessProcessMap;
    }

    private Map<String, FileDownProcessBo> getProcessByDownUrl(Map<String, FileDownProcessBo> accessProcessMap, List<ResourceDownloadBo>  downloadList){

        Map<String, FileDownProcessBo> downProcessMap = new HashMap<>();

        for(ResourceDownloadBo bo : downloadList){
            if(!StringUtils.hasText(bo.getUrl()))
                continue;

            if(MapUtils.isNotEmpty(accessProcessMap) && StringUtils.hasText(bo.getAccessUrl())) {
                if (accessProcessMap.containsKey(bo.getAccessUrl()))
                    continue;
            }

            String downUrlMd5 = Md5Util.md5DigestByString(bo.getUrl());
            FileDownProcessBo process = fileDownProcessManage.findByDownUrlMd5(downUrlMd5);
            if(process!=null) {
                process.setDownUrl(bo.getUrl());
                process.setRetryCount(0);
                process.setStatusEnum(FileDownProcessBo.StatusEnum.STARTED);
                downProcessMap.put(bo.getUrl(), process);
            }
        }

        return downProcessMap;
    }

    private Map<String, FileDownProcessBo> getNewProcess(AppBo appBo, Map<String, FileDownProcessBo> accessProcessMap, Map<String, FileDownProcessBo> downProcessMap, List<ResourceDownloadBo>  downloadList){
        Map<String, FileDownProcessBo> newProcessMap = new HashMap<>();

        for(ResourceDownloadBo bo : downloadList){

            if(MapUtils.isNotEmpty(accessProcessMap) && StringUtils.hasText(bo.getAccessUrl())) {
                if (accessProcessMap.containsKey(bo.getAccessUrl()))
                    continue;
            }

            if(MapUtils.isNotEmpty(downProcessMap) && StringUtils.hasText(bo.getUrl())) {
                if (downProcessMap.containsKey(bo.getUrl()))
                    continue;
            }

            String extension;
            try {
                extension = getFileExtension(bo.getUrl());
            }catch (Exception e){
                throw new RuntimeException("获取后缀名失败! url=" + bo.getUrl(), e);
            }
            String newAccessUrl = FileDownProcessBo.createAccessUrl(extension);

            FileDownProcessBo process = new FileDownProcessBo();
            process.setStatusEnum(FileDownProcessBo.StatusEnum.STARTED);
            process.setDownUrl(bo.getUrl());
            process.setType(bo.getFileType());
            process.setAccessUrl(newAccessUrl);
            process.setLastDownTime(new Date());
            process.setExtension(extension);
            process.setParams(bo.getParams());
            process.setIsProxy(appBo.getIsProxy());

            newProcessMap.put(process.getDownUrl(), process);
        }

        return newProcessMap;
    }



}
