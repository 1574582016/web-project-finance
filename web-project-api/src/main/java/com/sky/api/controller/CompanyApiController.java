package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.CompanyBaseInformation;
import com.sky.model.LearnEnglishWord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@RestController
@RequestMapping("/api/company")
public class CompanyApiController extends AbstractController {

    @LogRecord(name = "getCompanyBaseInformationList" ,description = "查询公司基本信息列表")
    @PostMapping("/getCompanyBaseInformationList")
    public Object getCompanyBaseInformationList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                    @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                    String listCode ,
                                    String listName ){
        Page selectedPage = companyBaseInformationService.getCompanyBaseInformationList( page , size , listCode, listName);
        return PageData(selectedPage);
    }

    @LogRecord(name = "editCompanyBaseInformation" ,description = "编辑公司基本信息")
    @PostMapping("/editCompanyBaseInformation")
    public Object editCompanyBaseInformation(@RequestBody CompanyBaseInformation body){
        System.out.println(body.toString());
        int num = companyBaseInformationService.selectCount(new EntityWrapper<CompanyBaseInformation>().where("list_code = {0}",body.getListCode()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该公司已存在！"));
        }
        companyBaseInformationService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getCompanyBaseInformationById" ,description = "根据ID获取公司基本信息")
    @PostMapping("/getCompanyBaseInformationById")
    public Object getCompanyBaseInformationById(String id){
        CompanyBaseInformation companyInfo = companyBaseInformationService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",companyInfo));
    }

    @LogRecord(name = "getCompanyOperateInformationList" ,description = "查询公司经营分析信息列表")
    @PostMapping("/getCompanyOperateInformationList")
    public Object getCompanyOperateInformationList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                                @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                                String listCode ){
        Page selectedPage = companyOperateInformationService.getCompanyOperateInformationList( page , size , listCode);
        return PageData(selectedPage);
    }
}
