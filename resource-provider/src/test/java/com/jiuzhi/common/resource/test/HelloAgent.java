package com.jiuzhi.common.resource.test;


import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Hashtable;

public class HelloAgent {

    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        HelloWorldService hello = new HelloWorldService();
        Hashtable<String, String> properties = new Hashtable<String, String>();

        properties.put("tttt", "ddd");
        ObjectName oname = ObjectName.getInstance("com.appleframework", properties);
        mbs.registerMBean(hello, oname);
        System.in.read();
    }
}
