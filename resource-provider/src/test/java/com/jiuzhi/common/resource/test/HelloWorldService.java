package com.jiuzhi.common.resource.test;

/**
 * Created by Administrator on 2017/3/17.
 */
public class HelloWorldService implements HelloWorldMBean {
    private String greeting = null;

    public HelloWorldService(String greeting) {
        this.greeting = greeting;
        System.out.println("HelloWorld=ddd");
    }

    public HelloWorldService() {
        this.greeting = "Hello World! I am Standard MBean";
    }

    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String getGreeting() {
        return greeting;
    }

    @Override
    public void printGreeting(User user) {
        System.out.println("printGreeting=" + user.getName() + greeting + " " + user.getdEnum());
    }
}
