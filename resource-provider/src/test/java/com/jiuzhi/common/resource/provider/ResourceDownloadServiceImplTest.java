package com.jiuzhi.common.resource.provider;

import com.jiuzhi.common.resource.constant.FileType;
import com.jiuzhi.common.resource.model.AppBo;
import com.jiuzhi.common.resource.model.ResourceDownloadBo;
import com.jiuzhi.common.resource.service.ResourceDownloadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class ResourceDownloadServiceImplTest {

    @Resource
    ResourceDownloadService resourceDownloadService;

    @Test
    public void downResource(){

        AppBo bo = new AppBo("192.168.1.127", 4399, "testApp", 123456L, "test", "aaa");
        ResourceDownloadBo downloadBo = new ResourceDownloadBo("d1", "http://ww1.sinaimg.cn/bmiddle/48e837eejw1fa3kmdtarxj20rs0rsadj.jpg", FileType.IMAGE.name(), "20161128/e43adf8d555544a1bf0dbf610f38a31e.jpg");
        ResourceDownloadBo downloadBo2 = new ResourceDownloadBo("d1", "http://ww1.sinaimg.cn/bmiddle/6ae35d94jw1fa5xyfew2zj20zk0qok00.jpg", FileType.IMAGE.name(), "20161128/7809ef8089ec45ce950c76a08317e1a8.jpg");

        System.out.println(resourceDownloadService.downResource(bo, downloadBo, downloadBo2));
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void downResource2(){

     //   System.out.println(resourceDownloadService.downResource("http://gslb.miaopai.com/stream/Alohe0a4C5teN4dSMNWjKA__.mp4?yx=&refer=weibo_app", FileType.MEDIA, "20161114/452e63b7e2754af499aea1aa9c7a3c8a.mp4"));
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void downResource3(){

      //  System.out.println(resourceDownloadService.downResource("http://gslb.miaopai.com/stream/asdfasegasegwe", FileType.MEDIA, "20161114/452e63b7e2754af499aea1aa9c7a3c8a111.mp4"));
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
