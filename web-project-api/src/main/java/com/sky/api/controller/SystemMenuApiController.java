package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.api.AbstractController;
import com.sky.annotation.LogRecord;
import com.sky.core.consts.SystemConst;
import com.sky.model.SystemMenu;
import com.sky.model.SystemRole;
import com.sky.model.SystemRoleMenu;
import com.sky.model.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2018/10/6.
 */
@RestController
@RequestMapping("/api/menu")
public class SystemMenuApiController extends AbstractController {

    @LogRecord(name = "getMenuTree" ,description = "查询菜单树状图")
    @PostMapping("/getMenuTree")
    public Object getMenuTree(){
        return ResponseEntity.ok(MapSuccess("查询成功！",systemMenuService.getMenuTree(null)));
    }

//    @LogRecord(name = "getMenuList" ,description = "查询首页菜单列表")
    @PostMapping("/getMenuList")
    public Object getMenuList(){
        SystemUser systemUser = (SystemUser)this.getSession().getAttribute(SystemConst.SYSTEMUSER);
        return ResponseEntity.ok(MapSuccess("查询成功！",systemMenuService.getMenuList(systemUser.getUserCode())));
    }

    @LogRecord(name = "getMenuInfo" ,description = "查询菜单信息")
    @PostMapping("/getMenuInfo")
    public Object getMenuInfo(String menuCode){
        SystemMenu systemMenu = systemMenuService.selectOne(new EntityWrapper<SystemMenu>().where("menu_code = {0}",menuCode));
        if(systemMenu.getParentCode().equals("1048460137181315074")){
            systemMenu.setMenuLevel(1);
        }else{
            systemMenu.setMenuLevel(2);
        }
        List<SystemRole> list = systemRoleService.getSystemRoleList(null ,menuCode);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("result" , systemMenu);
        map.put("role" ,list );
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "checkMenuName" ,description = "校验菜单是否已存在")
    @PostMapping("/checkMenuName")
    public Object checkMenuName(String parentCode , String menuName ,String menuCode){
        SystemMenu systemMenu = systemMenuService.selectOne(new EntityWrapper<SystemMenu>().where("menu_name = {0}",menuName).and("parent_code = {0}",parentCode));
        if(StringUtils.isNotBlank(menuCode)){
            systemMenu = null;
        }
        return ResponseEntity.ok(MapSuccess("查询成功！",systemMenu != null ? true : false));
    }

    @LogRecord(name = "editMenu" ,description = "编辑菜单信息")
    @PostMapping("/editMenu")
    public Object editMenu(@RequestBody SystemMenu body ,String roleId){
        String[] roleIds = {};
        if(StringUtils.isNotBlank(roleId)){
            roleIds = roleId.split(",");
        }
        if(StringUtils.isNotBlank(body.getMenuCode())){
            systemMenuService.update(body , new EntityWrapper<SystemMenu>().where("menu_code = {0}", body.getMenuCode()));
            saveRoleMenuList(roleIds , body.getMenuCode());
        }else{
            body.setMenuCode(IdWorker.getIdStr());
            systemMenuService.insert(body);
            SystemMenu systemMenu = systemMenuService.selectOne(new EntityWrapper<SystemMenu>().where("menu_name = {0}",body.getMenuName()).and("parent_code = {0}",body.getParentCode()));
            saveRoleMenuList(roleIds , systemMenu.getMenuCode());
        }

        return ResponseEntity.ok(MapSuccess("保存成功！", body.getMenuCode()));
    }

    private boolean saveRoleMenuList(String[] roleId , String menuCode){
        systemRoleMenuService.delete(new EntityWrapper<SystemRoleMenu>().where("menu_code = {0}", menuCode));
        List<SystemRoleMenu> list = new ArrayList<SystemRoleMenu>();
        for(String id : roleId){
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setMenuCode(menuCode);
            systemRoleMenu.setRoleCode(id);
            list.add(systemRoleMenu);
        }
        boolean addJust = false;
        if(list.size()>0){
            addJust = systemRoleMenuService.insertBatch(list);
        }
        return addJust;
    }
}
