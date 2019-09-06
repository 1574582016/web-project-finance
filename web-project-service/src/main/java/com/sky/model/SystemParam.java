package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
@Data
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

}
