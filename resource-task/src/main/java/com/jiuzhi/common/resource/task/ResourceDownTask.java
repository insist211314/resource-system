package com.jiuzhi.common.resource.task;

import com.appleframework.config.core.PropertyConfigurer;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.AbstractSimpleElasticJob;
import com.jiuzhi.common.resource.constant.FileType;
import com.jiuzhi.common.resource.constants.PropertiesKeyConstant;
import com.jiuzhi.common.resource.crawling.ResourceDownProcess;
import com.jiuzhi.common.resource.manage.FileDownProcessManage;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 定时器，1分钟执行一次,一次爬去一百条明星数据
 */
@Component("resourceDownTask")
public class ResourceDownTask extends AbstractSimpleElasticJob {

    private static final Logger logger = LoggerFactory.getLogger(ResourceDownTask.class);

    @Resource
    private FileDownProcessManage fileDownProcessManage;

    @Resource
    private ThreadPoolTaskExecutor crawlingExecutor;

    @Resource
    private ThreadPoolTaskExecutor videoExecutor;

    public void process(final JobExecutionMultipleShardingContext context) {

        //        List<Integer> itemList = context.getShardingItems();
        //		Map<Integer, String> itemMap = context.getShardingItemParameters();

        Integer interval = PropertyConfigurer.getInteger(PropertiesKeyConstant.TASK_DELAY_RANDOM_INTERVAL_KEY, 10);
        Integer processNum = PropertyConfigurer.getInteger(PropertiesKeyConstant.TASK_CRAWLING_PROCESS_NUM_KEY, 10);
        
        logger.info("ResourceDownTask执行。。。");

        try {
            Thread.sleep(1000 * (new Random().nextInt(interval)));
        } catch (InterruptedException e) {
            logger.error("", e);
        }

        //获取需要爬去的用户
        List<FileDownProcessBo> process = fileDownProcessManage.queryCrawlingProcess(processNum);

        if(CollectionUtils.isEmpty(process))
            return;

        for(FileDownProcessBo processBo : process) {
            if(!StringUtils.hasText(processBo.getDownUrl())){
                continue;
            }
            try {
                if (processBo.getTypeEnum() == FileType.IMAGE)
                    crawlingExecutor.execute(new ResourceDownProcess(processBo));
                if (processBo.getTypeEnum() == FileType.MEDIA)
                    videoExecutor.execute(new ResourceDownProcess(processBo));
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }

    }

}
