package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@TableName("system_user_role")
public class SystemUserRole extends MiddleModel<SystemUserRole> {

    @TableField("role_code")
    private String roleCode ;

    @TableField("user_code")
    private String userCode ;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
