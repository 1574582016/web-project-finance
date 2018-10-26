package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/12.
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<SystemUser> getSystemUserList(Pagination page , @Param("userName") String  userName, @Param("roleName") String roleName, @Param("roleCode") String roleCode);

    List<SystemUser> getSystemUserList(@Param("userName") String  userName, @Param("roleName") String roleName, @Param("roleCode") String roleCode);
}
