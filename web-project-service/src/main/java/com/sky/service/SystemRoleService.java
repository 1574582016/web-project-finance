package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SystemRole;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/9.
 */
public interface SystemRoleService extends IService<SystemRole> {

    List<SystemRole> getSystemRoleList( String userCode , String menuCode);
}
