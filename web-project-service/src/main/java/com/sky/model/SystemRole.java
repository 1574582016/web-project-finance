package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/9.
 */
@Data
@TableName("system_role")
public class SystemRole extends BaseModel<SystemRole> {

    @TableField("role_code")
    private String roleCode ;

    @TableField("role_name")
    private String roleName ;

    @TableField("describe")
    private String describe ;

}
