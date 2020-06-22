package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.core.utils.SaltUtils;
import com.sky.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @LogRecord(name = "getExpensesClassTree" ,description = "查询消费类型树状图")
    @PostMapping("/getExpensesClassTree")
    public Object getExpensesClassTree(){
        List<DailyExpensesClass> list = dailyExpensesClassService.selectList(new EntityWrapper<DailyExpensesClass>().where("isvalid = 1"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<DailyExpensesClass> iterator = list.iterator();
        while (iterator.hasNext()){
            DailyExpensesClass expensesClass = iterator.next();
            if(expensesClass.getParentId().intValue() == 0){
                TreeNode treeNode = new TreeNode(false ,false ,true ,false);
                treeNode.setText(expensesClass.getClassName());
                treeNode.setCode(expensesClass.getId().toString());
                treeNode.setParentCode(expensesClass.getParentId().toString());
                treeNode.setHref(expensesClass.getId().toString());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getExpensesClassRecursion(list , treeNode);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }


    private void getExpensesClassRecursion(List<DailyExpensesClass> list , TreeNode node){
        Iterator<DailyExpensesClass> iterator = list.iterator();
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        while (iterator.hasNext()){
            DailyExpensesClass expensesClass = iterator.next();
            if(expensesClass.getParentId().toString().equals(node.getCode())){
                TreeNode treeNode = new TreeNode(false ,false ,true ,false);
                treeNode.setText(expensesClass.getClassName());
                treeNode.setCode(expensesClass.getId().toString());
                treeNode.setParentCode(expensesClass.getParentId().toString());
                treeNode.setHref(expensesClass.getId().toString());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        node.setNodes(nodeList);
        for(TreeNode treeNode : nodeList){
            getExpensesClassRecursion(list , treeNode);
        }
    }

    @LogRecord(name = "checkExpensesClass" ,description = "校验消费是否已存在")
    @PostMapping("/checkExpensesClass")
    public Object checkExpensesClass(String parentCode , String menuName ,String menuCode){
        DailyExpensesClass expensesClass = dailyExpensesClassService.selectOne(new EntityWrapper<DailyExpensesClass>().where("class_name = {0}",menuName).and("parent_id = {0}",parentCode));
        if(StringUtils.isNotBlank(menuCode)){
            expensesClass = null;
        }
        return ResponseEntity.ok(MapSuccess("查询成功！",expensesClass != null ? true : false));
    }

    @LogRecord(name = "getExpensesClass" ,description = "查询类型信息")
    @PostMapping("/getExpensesClass")
    public Object getExpensesClass(Integer classId){
        DailyExpensesClass dailyExpensesClass = dailyExpensesClassService.selectById(classId);
        return ResponseEntity.ok(MapSuccess("查询成功！",dailyExpensesClass));
    }

    @LogRecord(name = "editExpensesClass" ,description = "编辑消费类型信息")
    @PostMapping("/editExpensesClass")
    public Object editExpensesClass(@RequestBody DailyExpensesClass body){
        dailyExpensesClassService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！", body.getId()));
    }

    @LogRecord(name = "getDailyExpensesRecordList" ,description = "查询套现记录列表")
    @PostMapping("/getDailyExpensesRecordList")
    public Object getDailyExpensesRecordList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                             @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                             String firstType ,
                                             String secondType ,
                                             String startDay ,
                                             String endDay ){
        Page selectedPage = dailyExpensesRecordService.getDailyExpensesRecordList(page , size, firstType, secondType , startDay , endDay);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getDailyExpensesRecord" ,description = "根据ID查询消费记录信息")
    @PostMapping("/getDailyExpensesRecord")
    public Object getDailyExpensesRecord(String id){
        DailyExpensesRecord expensesRecord = dailyExpensesRecordService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",expensesRecord));
    }

    @LogRecord(name = "editDailyExpensesRecord" ,description = "编辑消费记录信息")
    @PostMapping("/editDailyExpensesRecord")
    public Object editDailyExpensesRecord(@RequestBody DailyExpensesRecord body){
        dailyExpensesRecordService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }
}
