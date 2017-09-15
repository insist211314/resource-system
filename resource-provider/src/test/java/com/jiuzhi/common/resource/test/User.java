package com.jiuzhi.common.resource.test;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/20.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private DEnum dEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DEnum getdEnum() {
        return dEnum;
    }

    public void setdEnum(DEnum dEnum) {
        this.dEnum = dEnum;
    }
}
