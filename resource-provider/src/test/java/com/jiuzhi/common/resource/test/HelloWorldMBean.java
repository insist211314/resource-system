package com.jiuzhi.common.resource.test;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface HelloWorldMBean {
    public void setGreeting(String greeting);

    public String getGreeting();

    public void printGreeting(User user);
}
