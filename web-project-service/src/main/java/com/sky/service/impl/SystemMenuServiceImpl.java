package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.SystemMenuMapper;
import com.sky.model.SystemMenu;
import com.sky.service.SystemMenuService;
import com.sky.vo.SystemMenu_VO;
import com.sky.vo.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ThinkPad on 2018/10/6.
 */
@Service
@Transactional
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper ,SystemMenu> implements SystemMenuService {

    @Override
    public List<TreeNode> getMenuTree() {
        List<SystemMenu> menuList = baseMapper.selectList(null);
        List<TreeNode> firstList = new ArrayList<>();
        for(SystemMenu systemMenu : menuList){
            if(systemMenu.getParentCode().equals("1048460137181315074")){
                TreeNode treeNode = new TreeNode(false ,false ,true ,false);
                if(systemMenu.getIsvalid() == 1){
                    treeNode = new TreeNode(true ,false ,true ,false);
                }
                treeNode.setText(systemMenu.getMenuName());
                treeNode.setIcon(systemMenu.getMenuIcon());
                treeNode.setHref(systemMenu.getMenuCode());
                List<TreeNode> secondList = new ArrayList<>();
                for(SystemMenu systemMenu2 : menuList){
                    if(systemMenu.getMenuCode().equals(systemMenu2.getParentCode())){
                        TreeNode treeNode2 = new TreeNode(false ,false ,false ,false);
                        if(systemMenu2.getIsvalid() == 1){
                            treeNode2 = new TreeNode(true ,false ,false ,false);
                        }
                        treeNode2.setText(systemMenu2.getMenuName());
                        treeNode2.setIcon(systemMenu2.getMenuIcon());
                        treeNode2.setHref(systemMenu2.getMenuCode());
                        secondList.add(treeNode2);
                    }
                }
                treeNode.setNodes(secondList);
                firstList.add(treeNode);
            }
        }

        return firstList ;
    }

    @Override
    public List<SystemMenu_VO> getMenuList() {
        return baseMapper.getMenuList();
    }


}
