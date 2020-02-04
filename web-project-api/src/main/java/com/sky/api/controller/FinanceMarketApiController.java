package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.core.model.TreeNodeState;
import com.sky.model.FinanceMarket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
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
                treeNode.setHref(economyMarket.getMarketCode());
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
        List<FinanceMarket> list = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_type = 3"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<FinanceMarket> iterator = list.iterator();
        while (iterator.hasNext()){
            FinanceMarket economyMarket = iterator.next();
            if(economyMarket.getParentCode().equals("market")){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(economyMarket.getMarketName());
                treeNode.setCode(economyMarket.getMarketCode());
                treeNode.setParentCode(economyMarket.getParentCode());
                treeNode.setHref(economyMarket.getMarketCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getEconomyMarketRecursion(list , treeNode);
        }
        processTreeNode(nodeList);
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }


    private void processTreeNode(List<TreeNode> nodeList){
        for(TreeNode treeNode : nodeList){
            if(treeNode.getNodes().size() == 0){
                TreeNodeState treeNodeState = new TreeNodeState( false, false, true, false);
                treeNode.setState(treeNodeState);
            }
            processTreeNode(treeNode.getNodes());
        }
    }


    @LogRecord(name = "editFinanceMarket" ,description = "编辑行业信息")
    @PostMapping("/editFinanceMarket")
    public Object editFinanceMarket(@RequestBody FinanceMarket body){
        if(StringUtils.isBlank(body.getMarketCode())){
            List<FinanceMarket> existList = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_name = {0} and parent_code = {1} and isvalid = 1 and market_type = 3" , body.getMarketName() , body.getParentCode()));
            if(existList.size() > 0){
                return ResponseEntity.ok(MapError("已存在该行业分类"));
            }
            body.setMarketCode(IdWorker.getIdStr());
            financeMarketService.insert(body);
        }else{
            List<FinanceMarket> existList = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_name = {0} and parent_code = {1} and market_code != {2} and isvalid = 1 and market_type = 3" , body.getMarketName() , body.getParentCode() , body.getMarketCode()));
            if(existList.size() > 0){
                return ResponseEntity.ok(MapError("已存在该行业分类"));
            }
            financeMarketService.update(body, new EntityWrapper<FinanceMarket>().where("market_code = {0}", body.getMarketCode()));
        }
        return ResponseEntity.ok(MapSuccess("保存成功！", body.getMarketCode()));
    }

    @LogRecord(name = "getFinanceMarket" ,description = "查询行业信息")
    @PostMapping("/getFinanceMarket")
    public Object getFinanceMarket(String marketCode){
        FinanceMarket market = financeMarketService.selectOne(new EntityWrapper<FinanceMarket>().where("market_code = {0}" , marketCode));
        return ResponseEntity.ok(MapSuccess("查询成功！", market));
    }

}
