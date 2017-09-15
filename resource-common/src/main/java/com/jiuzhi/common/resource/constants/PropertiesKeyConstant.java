package com.jiuzhi.common.resource.constants;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface PropertiesKeyConstant {

    //任务延迟执行，随机的最大时间，单位秒
    String TASK_DELAY_RANDOM_INTERVAL_KEY = "task.delay.random.interval";

    //一次性爬取的任务数
    String TASK_CRAWLING_PROCESS_NUM_KEY = "task.crawling.process.num";

    //重试次数
    String TASK_CRAWLING_RETRY_COUNT = "task.crawling.retry.count";

    //URL失败多次后，是否继续重新下载
    String TASK_EXCEPTION_URL_RESET_KEY = "task.exception.url.reset";

}
