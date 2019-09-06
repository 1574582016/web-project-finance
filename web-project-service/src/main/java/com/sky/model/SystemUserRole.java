package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@Data
@TableName("system_user_role")
public class SystemUserRole extends MiddleModel<SystemUserRole> {

    @TableField("role_code")
    private String roleCode ;

    @TableField("user_code")
    private String userCode ;

}
