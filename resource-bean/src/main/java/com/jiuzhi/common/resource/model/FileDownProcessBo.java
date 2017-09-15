package com.jiuzhi.common.resource.model;

import com.jiuzhi.common.resource.constant.FileType;
import com.jiuzhi.common.resource.entity.FileDownProcess;
import com.jiuzhi.common.resource.utils.DateFormatUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/11/12.
 */
public class FileDownProcessBo extends FileDownProcess {

    public static String createAccessUrl(String fileSuffix) {
        return DateFormatUtil.nowString(DateFormatUtil.pattern8)+"/"+ UUID.randomUUID().toString().replace("-","") + "." + (StringUtils.hasText(fileSuffix)?fileSuffix:"");
    }

    public FileType getTypeEnum(){
        if(StringUtils.hasText(getType())){
            return FileType.valueOf(getType());
        }
        return null;
    }

    public void setDownUrl(String url){
        super.setDownUrl(url);
        try {
            super.setDownUrlMd5(DigestUtils.md5DigestAsHex(url.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("获取md5值失败 url=" + url, e);
        }
    }

    public void setAccessUrl(String url){
        super.setAccessUrl(url);
        try {
            super.setAccessUrlMd5(DigestUtils.md5DigestAsHex(url.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("获取md5值失败 url=" + url, e);
        }
    }

    public Boolean existsDiskFile(){
        if(!StringUtils.hasText(getFileDiskUrl()))
            return Boolean.FALSE;

        return new File(getFileDiskUrl()).exists();
    }

    public void setFileDiskUrl(String url){
        super.setFileDiskUrl(url);

        if(!StringUtils.hasText(url))
            return;
        File f = new File(url);
        if(!f.exists())
            return;
        FileInputStream is = null;
        try {
            is = new FileInputStream(f);
            super.setFileMd5(DigestUtils.md5DigestAsHex(is));
        }catch (Exception e){
            throw new RuntimeException("获取md5值失败 url=" + url, e);
        }finally {
            if(is !=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Integer getRetryCount(){
        if(super.getRetryCount()==null)
            return 0;
        return super.getRetryCount();
    }

    public void setStatusEnum(StatusEnum status){
        if(status!=null)
            super.setStatus(status.getVal());
    }

    public StatusEnum getStatusEnum(){
        if(getStatus()==null)
            return null;

        return StatusEnum.indexOf(getStatus());
    }

    public static enum StatusEnum{
        STARTED((short)1), DOWNLOADED((short)2), COMPLETED((short)3), EXCEPTION((short)4);
        private Short val;
        StatusEnum(short val){
            this.val = val;
        }

        public Short getVal() {
            return val;
        }

        public static StatusEnum indexOf(Short status){

            for(StatusEnum statusEnum : StatusEnum.values()){
                if(statusEnum.getVal() == status)
                    return statusEnum;
            }
            return null;
        }
    }

    public FileResourceBo adapterFileResource(){
        FileResourceBo bo = new FileResourceBo();
        bo.setAccessUrl(getAccessUrl());
        bo.setFileMd5(getFileMd5());
        bo.setExtension(getExtension());
        bo.setParams(getParams());
        bo.setDownUrlMd5(getDownUrlMd5());
        return bo;
    }
}
