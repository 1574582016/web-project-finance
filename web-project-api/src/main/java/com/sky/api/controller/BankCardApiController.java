package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.utils.SaltUtils;
import com.sky.model.BankCard;
import com.sky.model.BankLoadRecord;
import com.sky.model.SystemUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bankCard")
public class BankCardApiController extends AbstractController {

    @LogRecord(name = "getBankCardList" ,description = "查询银行信息列表")
    @PostMapping("/getBankCardList")
    public Object getBankCardList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                  @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                  String bankName ,
                                  String bankType){
        Page selectedPage = bankCardService.getBankCardList(page , size, bankName, bankType);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getBankCard" ,description = "根据ID查询机构信息")
    @PostMapping("/getBankCard")
    public Object getBankCard(String id){
        BankCard bankCard = bankCardService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",bankCard));
    }

    @LogRecord(name = "editBankCard" ,description = "编辑机构信息")
    @PostMapping("/editBankCard")
    public Object editBankCard(@RequestBody BankCard body){
        int num = bankCardService.selectCount(new EntityWrapper<BankCard>().where("bank_name = {0} and bank_type = {1}",body.getBankName() ,body.getBankType()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该机构已存在！"));
        }
        bankCardService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getBankLoadRecordList" ,description = "查询套现记录列表")
    @PostMapping("/getBankLoadRecordList")
    public Object getBankLoadRecordList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                        @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                        String bankName ,
                                        String bankType ,
                                        String startDay ,
                                        String endDay ){
        Page selectedPage = bankLoadRecordService.getBankLoadRecordList(page , size, bankName, bankType , startDay , endDay);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getBankLoadRecord" ,description = "根据ID查询套现记录信息")
    @PostMapping("/getBankLoadRecord")
    public Object getBankLoadRecord(String id){
        BankLoadRecord bankCard = bankLoadRecordService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",bankCard));
    }

    @LogRecord(name = "editBankLoadRecord" ,description = "编辑套现记录信息")
    @PostMapping("/editBankLoadRecord")
    public Object editBankLoadRecord(@RequestBody BankLoadRecord body){
        bankLoadRecordService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }
}
