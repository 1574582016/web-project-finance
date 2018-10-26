package com.sky.core.json;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by ThinkPad on 2018/10/10.
 */
public class JsonResult implements Serializable{

    /**
     *成功标志
     */
    private boolean success = false;

    /**
     * 状态码
     */
    private Integer code = 200;

    /**
     * 消息
     */
    private String message = "";

    /**
     * 传到前台的数据
     */
    private Map<String, Object> data;

    public JsonResult() {
    }

    public JsonResult(boolean success, Integer code, String message, Map<String, Object> data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}