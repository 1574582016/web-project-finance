package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.api.AbstractController;
import com.sky.model.SystemRole;
import com.sky.model.SystemRoleMenu;
import com.sky.vo.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2018/10/9.
 */
@RestController
@RequestMapping("/api/role")
public class SystemRoleApiController extends AbstractController {


    @PostMapping("/getRoleList")
    public Object getRoleList(String userCode){
        return ResponseEntity.ok(MapSuccess("查询成功！",systemRoleService.getSystemRoleList(userCode , null)));
    }

    @PostMapping("/getRoleInfo")
    public Object getRoleInfo(String roleCode){
        SystemRole systemRole = systemRoleService.selectOne(new EntityWrapper<SystemRole>().where("role_code = {0}",roleCode));
        List<TreeNode> list = systemMenuService.getMenuTree(roleCode);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("result" , systemRole);
        map.put("menu" ,list );
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @PostMapping("/checkRoleName")
    public Object checkRoleName(String menuName ,String roleCode){
        SystemRole systemRole = systemRoleService.selectOne(new EntityWrapper<SystemRole>().where("role_name = {0}",menuName));
        if(StringUtils.isNotBlank(roleCode)){
            systemRole = null;
        }
        return ResponseEntity.ok(MapSuccess("查询成功！",systemRole != null ? true : false));
    }

    @PostMapping("/editRole")
    public Object editRole(@RequestBody SystemRole body){
        if(StringUtils.isNotBlank(body.getRoleCode())){
            systemRoleService.update(body , new EntityWrapper<SystemRole>().where("role_code = {0}", body.getRoleCode()));
        }else{
            body.setRoleCode(IdWorker.getIdStr());
            systemRoleService.insert(body);
        }
        return ResponseEntity.ok(MapSuccess("保存成功！", body.getRoleCode()));
    }

    @PostMapping("/authRole")
    public Object authRole(String roleCode ,String menuCodes){
        systemRoleMenuService.delete(new EntityWrapper<SystemRoleMenu>().where("role_code = {0}" , roleCode));
        String menuCode[] = menuCodes.split(",");
        List<SystemRoleMenu> list = new ArrayList<SystemRoleMenu>();
        for(String id : menuCode){
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleCode(roleCode);
            systemRoleMenu.setMenuCode(id);
            list.add(systemRoleMenu);
        }
        systemRoleMenuService.insertBatch(list);
        return ResponseEntity.ok(MapSuccess("授权成功！",roleCode));
    }
}
