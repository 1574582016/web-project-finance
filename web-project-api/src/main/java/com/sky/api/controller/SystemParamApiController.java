package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.api.AbstractController;
import com.sky.model.SystemParam;
import com.sky.model.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2018/10/15.
 */
@RestController
@RequestMapping("/api/param")
public class SystemParamApiController extends AbstractController {

    @PostMapping("/getSystemParamList")
    public Object getSystemParamList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                    @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                    String paramIdentity ,
                                    String paramName ){
        Page selectedPage = systemParamService.getSystemParamList(page ,size , paramIdentity, paramName ,null);
        return PageData(selectedPage);
    }

    @PostMapping("/editSystemParam")
    public Object editSystemParam(@RequestBody SystemParam body){
        int num = systemParamService.selectCount(new EntityWrapper<SystemParam>().where("param_identity = {0}",body.getParamIdentity()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该参数已存在！"));
        }
        String paramCode = "";
        if(body.getId() == null){
            paramCode = IdWorker.getIdStr();
            body.setParamCode(paramCode);
        }else{
            paramCode = body.getParamCode();
        }
        body.setIsvalid(1);
        List<SystemParam> list = new ArrayList<SystemParam>();
        list.add(body);
        String subParamStr = body.getSubParamString();
        String[] params = subParamStr.split(",");
        for(String sub : params){
            String[] items = sub.split("=");
            System.out.println(sub);
            if(StringUtils.isNotBlank(items[1]) && StringUtils.isNotBlank(items[2])){
                SystemParam systemParam = new SystemParam();
                if(items[0].equals("undefined")){
                    systemParam.setParamCode(IdWorker.getIdStr());
                }else{
                    systemParam.setId(Integer.parseInt(items[0]));
                }
                systemParam.setParamIdentity(items[1]);
                systemParam.setParamName(items[2]);
                systemParam.setParentCode(paramCode);
                systemParam.setIsvalid(Integer.parseInt(items[3]));
                list.add(systemParam);
            }
        }
        System.out.println(list.toString());
        systemParamService.insertOrUpdateBatch(list);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @PostMapping("/getSystemParamInfo")
    public Object getSystemParamInfo(String id){
        SystemParam systemParam = systemParamService.selectById(id);
        List<SystemParam> list = systemParamService.getSystemParamList(null , null , systemParam.getParamCode());
        systemParam.setChildParam(list);
        return ResponseEntity.ok(MapSuccess("查询成功",systemParam));
    }

    @PostMapping("/getParamListByIndetity")
    public Object getParamListByIndetity(String indetity){
        return ResponseEntity.ok(MapSuccess("查询成功",systemParamService.getParamListByIdentity(indetity)));
    }
}
