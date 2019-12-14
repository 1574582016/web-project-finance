package com.sky.core.utils;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

/**
 * Created by ThinkPad on 2019/12/14.
 */
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;// 名字
    private Vector<Object> data;// 数据值ֵ

    public Serie() {

    }

    /**
     *
     * @param name
     *            名称（线条名称）
     * @param data
     *            数据（线条上的所有数据值）
     */
    public Serie(String name, Vector<Object> data) {

        this.name = name;
        this.data = data;
    }

    /**
     *  @param name
     *            名称（线条名称）
     * @param array
     */
    public Serie(String name, List<BigDecimal> array) {
        this.name = name;
        if (array != null) {
            data = new Vector<Object>(array.size());
            for (int i = 0; i < array.size(); i++) {
                data.add(array.get(i));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Object> getData() {
        return data;
    }

    public void setData(Vector<Object> data) {
        this.data = data;
    }
}
