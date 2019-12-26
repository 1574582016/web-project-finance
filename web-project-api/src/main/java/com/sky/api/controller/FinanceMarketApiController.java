package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.FinanceMarket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ThinkPad on 2018/10/17.
 */
@RestController
@RequestMapping("/api/finance")
public class FinanceMarketApiController extends AbstractController {

    @LogRecord(name = "getFinanceMarketTree" ,description = "查询金融市场树状图")
    @PostMapping("/getFinanceMarketTree")
    public Object getFinanceMarketTree(){
        List<FinanceMarket> list = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_type = 1"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<FinanceMarket> iterator = list.iterator();
        while (iterator.hasNext()){
            FinanceMarket economyMarket = iterator.next();
            if(economyMarket.getParentCode().equals("market")){
               TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(economyMarket.getMarketName());
                treeNode.setCode(economyMarket.getMarketCode());
                treeNode.setParentCode(economyMarket.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getEconomyMarketRecursion(list , treeNode);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }


    private void getEconomyMarketRecursion(List<FinanceMarket> list , TreeNode node){
        Iterator<FinanceMarket> iterator = list.iterator();
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        while (iterator.hasNext()){
            FinanceMarket economyMarket = iterator.next();
            if(economyMarket.getParentCode().equals(node.getCode())){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(economyMarket.getMarketName());
                treeNode.setCode(economyMarket.getMarketCode());
                treeNode.setParentCode(economyMarket.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        node.setNodes(nodeList);
        for(TreeNode treeNode : nodeList){
            getEconomyMarketRecursion(list , treeNode);
        }
    }


    @LogRecord(name = "getPlatformSectorTree" ,description = "查询平台市场树状图")
    @PostMapping("/getPlatformSectorTree")
    public Object getPlatformSectorTree(){
        List<FinanceMarket> list = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_type = 2"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<FinanceMarket> iterator = list.iterator();
        while (iterator.hasNext()){
            FinanceMarket economyMarket = iterator.next();
            if(economyMarket.getParentCode().equals("market")){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(economyMarket.getMarketName());
                treeNode.setCode(economyMarket.getMarketCode());
                treeNode.setParentCode(economyMarket.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getEconomyMarketRecursion(list , treeNode);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }

}
