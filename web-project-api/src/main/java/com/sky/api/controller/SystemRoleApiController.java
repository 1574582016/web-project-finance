package com.sky.api.controller;

import com.sky.api.AbstractController;
import com.sky.model.SystemRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
