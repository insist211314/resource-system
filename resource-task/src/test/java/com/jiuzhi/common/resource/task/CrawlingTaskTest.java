package com.jiuzhi.common.resource.task;

import com.jiuzhi.common.resource.dao.extend.FileJobExtendDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class CrawlingTaskTest {
    @Resource
    private ResourceDownTask resourceDownTask;

    @Resource
    private FileJobExtendDao fileJobExtendDao;

    @Test
    public void test1(){
        try {
            resourceDownTask.process(null);
        	Thread.sleep(1000);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        fileJobExtendDao.updateByCompletedDownload(12254L, true);
    }
}
