package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/21.
 */
@Data
@TableName("futures_class")
public class FuturesClass extends BaseModel<FuturesClass> {

    /**
     * 期货编码
     */
    @TableField("futures_code")
    private String futuresCode ;

    /**
     * 期货名称
     */
    @TableField("futures_name")
    private String futuresName ;

    /**
     * 一级分类
     */
    @TableField("first_class")
    private String firstClass ;

    /**
     * 二级分类
     */
    @TableField("second_class")
    private String secondClass ;
}
