package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.consts.SystemConst;
import com.sky.model.SystemUser;
import com.sky.model.SystemUserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@RestController
@RequestMapping("/api/user")
public class SystemUserApiController extends AbstractController {

    @LogRecord(name = "getSystemUserList" ,description = "查询系统用户列表")
    @PostMapping("/getSystemUserList")
    public Object getSystemUserList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                     @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                     String userName ,
                                     String realName ,
                                    String roleCode){
        Page selectedPage = systemUserService.getSystemUserList(page , size, userName, realName , roleCode);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getSystemUserInfo" ,description = "根据ID查询系统用户信息")
    @PostMapping("/getSystemUserInfo")
    public Object getSystemUserInfo(String id){
        SystemUser systemUser = systemUserService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",systemUser));
    }

    @LogRecord(name = "editSystemUser" ,description = "编辑系统用户信息")
    @PostMapping("/editSystemUser")
    public Object editSystemUser(@RequestBody SystemUser body){
        int num = systemUserService.selectCount(new EntityWrapper<SystemUser>().where("user_name = {0}",body.getUserName()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该用户已存在！"));
        }
        if(body.getId() == null){
            body.setUserCode(IdWorker.getIdStr());
        }
        systemUserService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "linkUserRole" ,description = "为用户赋予角色")
    @PostMapping("/linkUserRole")
    public Object linkUserRole(String userCode , String roleId){
           if(StringUtils.isNotEmpty(roleId)){
               String[] ids = roleId.split(",");
               List<SystemUserRole> list = new ArrayList<SystemUserRole>();
               for(String id : ids){
                   SystemUserRole systemUserRole = new SystemUserRole();
                   systemUserRole.setRoleCode(id);
                   systemUserRole.setUserCode(userCode);
                   list.add(systemUserRole);
               }
               systemUserRoleService.delete(new EntityWrapper<SystemUserRole>().where("user_code = {0}" , userCode));
               systemUserRoleService.insertBatch(list);
               return ResponseEntity.ok(MapSuccess("保存成功！"));
           }else{
               return ResponseEntity.ok(MapError("请选择角色！"));
           }
    }

    @LogRecord(name = "login" ,description = "用户登录接口")
    @PostMapping("/login")
    public Object login(String userName ,String password){
        SystemUser systemUser = systemUserService.selectOne(new EntityWrapper<SystemUser>().where("user_name = {0}" ,userName).and("password = {0} " , password));
        if (systemUser == null){
            return ResponseEntity.ok(MapError("用户不存在，请检查账号和密码是否正确！"));
        }
        this.getSession().setAttribute(SystemConst.SYSTEMUSER , systemUser);
        return ResponseEntity.ok(MapSuccess("登陆成功成功"));
    }
}
