package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.FinanceMarket;
import com.sky.model.StockPoolClass;
import com.sky.model.StockPoolSecondClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@RestController
@RequestMapping("/api/stockPool")
public class StockPoolApiController extends AbstractController {

    @LogRecord(name = "getStockPoolClassTree" ,description = "查询股票池树状图")
    @PostMapping("/getStockPoolClassTree")
    public Object getStockPoolClassTree(){
        List<StockPoolClass> list = stockPoolClassService.selectList(new EntityWrapper<StockPoolClass>().where("isvalid = 1").orderBy("pool_code asc").orderBy("pool_sore asc"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<StockPoolClass> iterator = list.iterator();
        while (iterator.hasNext()){
            StockPoolClass poolClass = iterator.next();
            if(poolClass.getParentCode().equals("1000000")){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(poolClass.getPoolName());
                treeNode.setCode(poolClass.getPoolCode());
                treeNode.setParentCode(poolClass.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getStoolFirstList(list , treeNode);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }

    private void getStoolFirstList(List<StockPoolClass> list , TreeNode node){
        Iterator<StockPoolClass> iterator = list.iterator();
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        while (iterator.hasNext()){
            StockPoolClass poolClass = iterator.next();
            if(poolClass.getParentCode().equals(node.getCode())){
                TreeNode treeNode = new TreeNode(false ,false ,true ,false);
                treeNode.setText(poolClass.getPoolName());
                treeNode.setCode(poolClass.getPoolCode());
                treeNode.setParentCode(poolClass.getParentCode());
                treeNode.setHref("/api/stockPool/getStockPoolSecondClassTree?poolCode="+ poolClass.getPoolCode() + "&poolName=" + poolClass.getPoolName() + "&parentCode=" + poolClass.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        node.setNodes(nodeList);
        for(TreeNode treeNode : nodeList){
            getStoolFirstList(list , treeNode);
        }
    }

    @LogRecord(name = "getStockPoolSecondClassTree" ,description = "查询股票池二级树状图")
    @PostMapping("/getStockPoolSecondClassTree")
    public Object getStockPoolSecondClassTree(String poolCode ,String poolName ,String parentCode){
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeNode treeNode = new TreeNode(false ,false ,true ,false);
        treeNode.setText(poolName);
        treeNode.setCode(poolCode);
        treeNode.setParentCode(parentCode);
        List<StockPoolSecondClass> list = stockPoolSecondClassService.selectList(new EntityWrapper<StockPoolSecondClass>().where("isvalid = 1 and first_code = {0}",poolCode).orderBy("second_code asc").orderBy("scond_sore asc"));
        List<TreeNode> nodeList2 = new ArrayList<TreeNode>();
        for(StockPoolSecondClass secondClass : list){
            TreeNode treeNodes = new TreeNode(false ,false ,true ,false);
            treeNodes.setText(secondClass.getSecondName());
            treeNodes.setCode(secondClass.getSecondCode());
            treeNodes.setParentCode(secondClass.getFirstCode());
            treeNodes.setHref("/api/stock/getStockCompanyBaseList?stockSector=" + poolName + "&middleContrySector=" + secondClass.getSecondName());
            nodeList2.add(treeNodes);
        }
        treeNode.setNodes(nodeList2);
        nodeList.add(treeNode);
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }
}
