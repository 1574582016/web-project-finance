package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.StockCompanyNoticeClass;
import com.sky.model.StockPoolClass;
import com.sky.model.StockPoolSecondClass;
import com.sky.vo.StockNoticeCompany_VO;
import com.sky.vo.StockNoticeDetail_VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/5.
 */
@RestController
@RequestMapping("/api/notice")
public class StockNoticeApiController extends AbstractController {

    @LogRecord(name = "getStockNoticeClassTree" ,description = "查询股票公告树状图")
    @PostMapping("/getStockNoticeClassTree")
    public Object getStockNoticeClassTree(){
        List<StockCompanyNoticeClass> list = stockCompanyNoticeClassService.selectList(new EntityWrapper<StockCompanyNoticeClass>().where("isvalid = 1").orderBy("class_level desc").orderBy("order_num asc"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        for(StockCompanyNoticeClass noticeClass : list){
            if(noticeClass.getParentCode().equals("1000000")){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(noticeClass.getClassName());
                treeNode.setCode(noticeClass.getClassCode());
                treeNode.setParentCode(noticeClass.getParentCode());
                nodeList.add(treeNode);
            }
        }

        for(TreeNode treeNode : nodeList){
            List<TreeNode> subNodeList = new ArrayList<TreeNode>();
            for(StockCompanyNoticeClass noticeClass : list){
                if(treeNode.getCode().equals(noticeClass.getParentCode())){
                    TreeNode subTreeNode = new TreeNode(false ,false ,true ,false);
                    subTreeNode.setText(noticeClass.getClassName());
                    subTreeNode.setCode(noticeClass.getClassCode());
                    subTreeNode.setParentCode(noticeClass.getParentCode());
                    subTreeNode.setHref("/api/notice/getStockNoticeClassList?parentCode="+ noticeClass.getClassCode());
                    subNodeList.add(subTreeNode);
                }
            }
            treeNode.setNodes(subNodeList);
        }

        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }


    @LogRecord(name = "getStockNoticeClassList" ,description = "查询股票公告分类信息")
    @PostMapping("/getStockNoticeClassList")
    public Object getStockNoticeClassList(String className ,String parentCode){
        Wrapper<StockCompanyNoticeClass> wrapper = new EntityWrapper<>();
        wrapper.where("parent_code = {0}" , parentCode);
        if(StringUtils.isNotBlank(className)){
            wrapper.where("class_name = {0}" , className);
        }
        Page<StockCompanyNoticeClass> list = stockCompanyNoticeClassService.selectPage(new Page<StockCompanyNoticeClass>(1,100) ,wrapper.orderBy("class_level , order_num"));
        return PageData(list);
    }

    @LogRecord(name = "getStockNoticeClass" ,description = "查询股票公告分类信息")
    @PostMapping("/getStockNoticeClass")
    public Object getStockNoticeClass(String classCode){
        StockCompanyNoticeClass noticeClass = stockCompanyNoticeClassService.selectOne(new EntityWrapper<StockCompanyNoticeClass>().where("class_code = {0}" , classCode));
        return ResponseEntity.ok(MapSuccess("查询成功", noticeClass));
    }


    @LogRecord(name = "editStockNoticeClass" ,description = "修改股票公告分类信息")
    @PostMapping("/editStockNoticeClass")
    public Object editStockNoticeClass(Integer id , Integer classLevel , Integer orderNum, String classDesc){
        StockCompanyNoticeClass noticeClass = stockCompanyNoticeClassService.selectById(id);
        if(noticeClass == null){
            ResponseEntity.ok(MapError("没有该公告分类"));
        }
        noticeClass.setId(id);
        noticeClass.setClassLevel(classLevel);
        noticeClass.setOrderNum(orderNum);
        noticeClass.setClassDesc(classDesc);
        stockCompanyNoticeClassService.updateById(noticeClass);
        return ResponseEntity.ok(MapSuccess("操作成功"));
    }

    @LogRecord(name = "getStockNoticeCompanyList" ,description = "查询公告公司列表")
    @PostMapping("/getStockNoticeCompanyList")
    public Object getStockNoticeCompanyList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                            String stockCode ,
                                            String stockName ,
                                            String startDay ,
                                            String endDay ){
        Page selectedPage = stockCompanyNoticeService.getStockNoticeCompanyList(page ,size , stockCode, stockName,startDay , endDay);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getStockNoticeDetailList" ,description = "查询公司公告详情列表")
    @PostMapping("/getStockNoticeDetailList")
    public Object getStockNoticeDetailList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                            String stockCode ,
                                            String startDay ,
                                            String endDay ){
        Page selectedPage = stockCompanyNoticeService.getStockNoticeDetailList(page ,size , stockCode ,startDay , endDay);
        return PageData(selectedPage);
    }
}
