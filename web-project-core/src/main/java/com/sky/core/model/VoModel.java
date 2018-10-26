package com.sky.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by lxl on 2018/9/20.
 * 顶层基类，用于实体，vo继承实现toString方法
 */
//@MappedSuperclass
public class VoModel implements Serializable{

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
