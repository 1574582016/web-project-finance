package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;

/**
 * Created by ThinkPad on 2018/10/9.
 */
@TableName("system_role_menu")
public class SystemRoleMenu extends MiddleModel<SystemRoleMenu> {

    @TableField("role_code")
    private String roleCode ;

    @TableField("menu_code")
    private String menuCode ;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getRoleCode() {

        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
