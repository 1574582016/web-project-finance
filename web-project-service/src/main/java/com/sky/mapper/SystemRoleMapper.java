package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/9.
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    List<SystemRole> getSystemRoleList(@Param("userCode") String userCode ,@Param("menuCode") String menuCode);
}
