package com.jiuzhi.common.resource.utils;

/**
 * Created by Administrator on 2016/10/25.
 */
public class DelayUtil<T> {

    private Long startTime = System.currentTimeMillis();

    private Long millisecond;

    private Callback<T> callback;

    private T result;

    public DelayUtil(){}

    public DelayUtil(Long millisecond, T result){
        this.millisecond = millisecond;
        this.result = result;
    }

    public DelayUtil(Long millisecond, Callback callback){
        this.millisecond = millisecond;
        this.callback = callback;
        this.result = (T) callback.invoke();
    }

    public DelayUtil(Long millisecond){
        this.millisecond = millisecond;
    }

    public Boolean isTimeout(){
        if(System.currentTimeMillis() - startTime > millisecond){
            startTime = System.currentTimeMillis();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isTimeout(Long timeoutTime){
        if(System.currentTimeMillis() - startTime > timeoutTime){
            startTime = System.currentTimeMillis();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public T get(T result){
        if(System.currentTimeMillis() - startTime > millisecond){
            startTime = System.currentTimeMillis();
            this.result = result;
        }
        return this.result;
    }

    public T get(){
        if(System.currentTimeMillis() - startTime > millisecond){
            startTime = System.currentTimeMillis();
            result = callback.invoke();
        }
        return result;
    }

    public static void main(String[] args) {
        //demo1，是否超时
        //DelayUtil delayUtil = new DelayUtil(1000L);
        //delayUtil.isTimeout();

        //demo2，延迟获取对象,1000毫秒内获取a，超过则获取b
        //DelayUtil delayUtil = new DelayUtil(1000L,"a");
        //delayUtil.get("b");

        //demo3,是否超时
        //DelayUtil delayUtil = new DelayUtil();
        //System.out.println(delayUtil.isTimeout(1000L));
    }

    public Callback<T> getCallback() {
        return callback;
    }

    public void setCallback(Callback<T> callback) {
        this.callback = callback;
    }
}
