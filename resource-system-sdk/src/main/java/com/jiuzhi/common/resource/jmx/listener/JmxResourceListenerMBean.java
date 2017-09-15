package com.jiuzhi.common.resource.jmx.listener;


/**
 * Created by Administrator on 2017/3/21.
 */
public interface JmxResourceListenerMBean {

    void receive(String id,String type, Boolean isSuccessed);

}
