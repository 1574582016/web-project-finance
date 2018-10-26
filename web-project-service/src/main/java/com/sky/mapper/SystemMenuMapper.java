package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.SystemMenu;
import com.sky.vo.SystemMenu_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/6.
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    List<SystemMenu_VO> getMenuList();
}
