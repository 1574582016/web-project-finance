package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.SystemUserRoleMapper;
import com.sky.model.SystemUserRole;
import com.sky.service.SystemUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@Service
@Transactional
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper,SystemUserRole> implements SystemUserRoleService {
}
