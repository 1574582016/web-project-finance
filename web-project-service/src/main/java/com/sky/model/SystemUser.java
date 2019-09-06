package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@Data
@TableName("system_user")
public class SystemUser extends BaseModel<SystemUser> {

    @TableField("user_code")
    private String userCode ;

    @TableField("user_name")
    private String userName ;

    @TableField("real_name")
    private String realName ;

    @TableField("password")
    private String password ;

    @TableField("head_picture")
    private String headPicture ;

    @TableField("phone")
    private String phone ;

    @TableField("email")
    private String email ;

    @TableField(exist = false)
    private String roleName ;


}
