package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/29.
 */
@TableName("stock_market_class")
public class StockMarketClass extends BaseModel<StockMarketClass> {

    /**
     *类型编码
     */
    @TableField("class_code")
    private String classCode ;

    /**
     *类型名称
     */
    @TableField("class_name")
    private String className ;

    /**
     *大类名称:行业，地域，题材
     */
    @TableField("class_type")
    private String classType ;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
