package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SystemUser;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/12.
 */
public interface SystemUserService extends IService<SystemUser> {

    Page<SystemUser> getSystemUserList(Integer page , Integer size , String  userName, String roleName, String roleCode);

    List<SystemUser> getSystemUserList(String  userName, String roleName, String roleCode);
}
