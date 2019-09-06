package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/6.
 */
@Data
@TableName("system_menu")
public class SystemMenu extends BaseModel<SystemMenu> {

    @TableField("menu_code")
    private String menuCode ;

    @TableField("menu_name")
    private String menuName ;

    @TableField("parent_code")
    private String parentCode ;

    @TableField("menu_url")
    private String menuUrl ;

    @TableField("menu_icon")
    private String menuIcon ;

    @TableField("order_num")
    private String orderNum ;

    @TableField(exist = false)
    private Integer menuLevel ;

}
