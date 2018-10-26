package com.sky.vo;

import com.sky.core.model.VoModel;

/**
 * Created by ThinkPad on 2018/10/15.
 */
public class SystemParam_VO extends VoModel {

    private String paramName ;


    private String paramIdentity ;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamIdentity() {
        return paramIdentity;
    }

    public void setParamIdentity(String paramIdentity) {
        this.paramIdentity = paramIdentity;
    }
}
