package com.sky.test;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.api.controller.StockCompanyApiController;
import com.sky.core.json.JsonResult;
import com.sky.core.utils.ChartUtils;
import com.sky.core.utils.Serie;
import com.sky.mapper.StockCompanyProfitMapper;
import com.sky.model.StockCompanyProduct;
import com.sky.service.StockCompanyProfitService;
import com.sky.vo.StockCompanyProfitVO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/12/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateImgeTest {

    public CreateImgeTest() {
    }

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService ;

//    @Test
    public DefaultCategoryDataset createDataset() {
        List<StockCompanyProfitVO> list = stockCompanyProfitService.getCompanyProfitGrowList("601360");
        List<String> title = new ArrayList<>();

        List<BigDecimal> totalProfit = new ArrayList<>();
        List<BigDecimal> mainBusinessProfit = new ArrayList<>();
        List<BigDecimal> viceBusinessProfit = new ArrayList<>();
        List<BigDecimal> otherProfit = new ArrayList<>();

        for(StockCompanyProfitVO profit : list){
            title.add(profit.getPublishYear());
            totalProfit.add(profit.getTotalProfit());
            mainBusinessProfit.add(profit.getMainBusinessProfit());
            viceBusinessProfit.add(profit.getViceBusinessProfit());
            otherProfit.add(profit.getOtherProfit());
        }
        String[] categories = new String[title.size()];
        title.toArray(categories);
        Vector<Serie> series = new Vector<Serie>();
        // 柱子名称：柱子所有的值集合
        series.add(new Serie("总利润", totalProfit));
        series.add(new Serie("主营利润", mainBusinessProfit));
        series.add(new Serie("辅营利润", viceBusinessProfit));
        series.add(new Serie("其他利润", otherProfit));
        // 1：创建数据集合
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public JFreeChart createChart() {
        // 2：创建Chart[创建不同图形]
        JFreeChart chart = ChartFactory.createLineChart("利润增长", "", "收益（亿）", createDataset());
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[[采用不同渲染]]
        ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
//        chart.setTitle("利润增长");
        // 6:使用chartPanel接收
        return chart;
    }

    @Test
    public void main() {
//        final JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1024, 420);
//        frame.setLocationRelativeTo(null);

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                // 创建图形
//                JFreeChart chartPanel = new CreateImgeTest().createChart();
//                frame.getContentPane().add(chartPanel);
//                frame.setVisible(true);
//            }
//        });
//
        JFreeChart chartPanel = createChart();
        try {
            ChartUtils.saveAsFile(chartPanel , "E:/nue.png" , 1024 , 420);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
