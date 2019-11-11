package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.StockChoseClass;
import com.sky.model.StockTigerList;
import com.sky.vo.SpecialTreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/7.
 */
@RestController
@RequestMapping("/api/stockChose")
public class StockChoseClassApiController extends AbstractController {

    @LogRecord(name = "getStockChoseClassList" ,description = "根据父级ID获取选股类型信息")
    @PostMapping("/getStockChoseClassList")
    public Object getStockChoseClassList(String parentCode){
        Wrapper<StockChoseClass> wrapper = new EntityWrapper<>();
        wrapper.where("parent_code = {0}" , parentCode);
        List<StockChoseClass> list = stockChoseClassService.selectList(wrapper);
        return ResponseEntity.ok(MapSuccess("查询成功",list));
    }

    @LogRecord(name = "getStockChoseClassTree" ,description = "根据父级ID获取选股类型信息")
    @PostMapping("/getStockChoseClassTree")
    public Object getStockChoseClassTree(String parentCode){
        List<StockChoseClass> list0 = stockChoseClassService.selectList(new EntityWrapper<StockChoseClass>().where("parent_code = 'xgzb'"));
        SpecialTreeNode treeNode = new SpecialTreeNode();
        treeNode.setName("选股分类");
        List<SpecialTreeNode> children1 = new ArrayList<>();
        list0.forEach(note0->{
            SpecialTreeNode treeNode1 = new SpecialTreeNode();
            treeNode1.setName(note0.getClassName());
            List<SpecialTreeNode> children2 = new ArrayList<>();
            List<StockChoseClass> list1 = stockChoseClassService.selectList(new EntityWrapper<StockChoseClass>().where("parent_code = {0}" , note0.getClassCode()));
            list1.forEach(note1->{
                SpecialTreeNode treeNode2 = new SpecialTreeNode();
                treeNode2.setName(note1.getClassName());
                List<SpecialTreeNode> children3 = new ArrayList<>();
                List<StockChoseClass> list2 = stockChoseClassService.selectList(new EntityWrapper<StockChoseClass>().where("parent_code = {0}" , note1.getClassCode()));
                list2.forEach(note2->{
                    SpecialTreeNode treeNode3 = new SpecialTreeNode();
                    treeNode3.setName(note2.getClassName());
                    List<SpecialTreeNode> children4 = new ArrayList<>();
                    List<StockChoseClass> list3 = stockChoseClassService.selectList(new EntityWrapper<StockChoseClass>().where("parent_code = {0}" , note2.getClassCode()));
                    list3.forEach(note3->{
                        SpecialTreeNode treeNode4 = new SpecialTreeNode();
                        treeNode4.setName(note3.getClassName());
                        children4.add(treeNode4);
                    });
                    treeNode3.setChildren(children4);
                    children3.add(treeNode3);
                });
                treeNode2.setChildren(children3);
                children2.add(treeNode2);
            });
            treeNode1.setChildren(children2);
            children1.add(treeNode1);
        });
        treeNode.setChildren(children1);
        return ResponseEntity.ok(MapSuccess("查询成功",treeNode));
    }

    @LogRecord(name = "getStockTigerList" ,description = "查询龙虎榜信息列表")
    @PostMapping("/getStockTigerList")
    public Object getStockTigerList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                    @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                    String stockCode ,
                                    String stockName ,
                                    String startDay ,
                                    String endDay){
        Page selectedPage = stockTigerListService.getStockTigerList( page , size , stockCode, stockName  ,startDay ,endDay );
        return PageData(selectedPage);
    }

    @LogRecord(name = "editStockTigerList" ,description = "查询龙虎榜信息列表")
    @PostMapping("/editStockTigerList")
    public Object editStockTigerList(Integer id ,String unusualReason, String chooseReason){
        if(id == null){
            return ResponseEntity.ok(MapSuccess("ID不能为空"));
        }
        StockTigerList tigerList = stockTigerListService.selectById(id);
        tigerList.setUnusualReason(unusualReason);
        tigerList.setChooseReason(chooseReason);
        return stockTigerListService.updateById(tigerList) ? ResponseEntity.ok(MapSuccess("修改成功")) : ResponseEntity.ok(MapSuccess("修改失败"));
    }

    @LogRecord(name = "getStockChoseStrategyList" ,description = "查询策略列表")
    @PostMapping("/getStockChoseStrategyList")
    public Object getStockChoseStrategyList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                            String isUpper ,
                                            String isTop,
                                            String isMiddle,
                                            String isBottom,
                                            String minClosePrice,
                                            String maxClosePrice,
                                            String minStandarPrice,
                                            String maxStandarPrice,
                                            String forthSector ,
                                            String startDay ,
                                            String endDay){
        Page selectedPage = stockChoseStrategyService.getStockChoseStrategyList( page, size, isUpper , isTop, isMiddle, isBottom, minClosePrice, maxClosePrice, minStandarPrice, maxStandarPrice, forthSector , startDay , endDay);
        return PageData(selectedPage);
    }
}
