package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
@TableName("system_param")
public class SystemParam extends BaseModel<SystemParam> {

    @TableField("param_code")
    private String paramCode ;

    @TableField("param_name")
    private String paramName ;

    @TableField("parent_code")
    private String parentCode ;

    @TableField("param_identity")
    private String paramIdentity ;

    @TableField(exist = false)
    private List<SystemParam> childParam;

    @TableField(exist = false)
    private String subParamString ;

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParamIdentity() {
        return paramIdentity;
    }

    public void setParamIdentity(String paramIdentity) {
        this.paramIdentity = paramIdentity;
    }

    public List<SystemParam> getChildParam() {
        return childParam;
    }

    public void setChildParam(List<SystemParam> childParam) {
        this.childParam = childParam;
    }

    public String getSubParamString() {
        return subParamString;
    }

    public void setSubParamString(String subParamString) {
        this.subParamString = subParamString;
    }
}
