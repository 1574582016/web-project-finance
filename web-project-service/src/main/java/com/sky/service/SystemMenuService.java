package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SystemMenu;
import com.sky.vo.SystemMenu_VO;
import com.sky.vo.TreeNode;

import java.util.List;


/**
 * Created by ThinkPad on 2018/10/6.
 */
public interface SystemMenuService extends IService<SystemMenu> {

    List<TreeNode> getMenuTree(String roleCode);

    List<SystemMenu_VO> getMenuList(String userCode);

}
