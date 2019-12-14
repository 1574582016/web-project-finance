package com.sky;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.sky.core.utils.ChartUtils;
import com.sky.core.utils.FileUtils;
import com.sky.core.utils.ImgUtils;
import com.sky.core.utils.Serie;
import com.sky.model.StockCompanyAsset;
import com.sky.model.StockCompanyProduct;
import com.sky.model.StockCompanySector;
import com.sky.model.StockRiseRate;
import com.sky.service.*;
import com.sky.vo.CreateCompanyWorld_VO;
import com.sky.vo.StockCompanyAssetVO;
import com.sky.vo.StockCompanyProfitVO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/12/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordExportTest {

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService;

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService ;

    @Autowired
    private StockCompanyAssetService stockCompanyAssetService ;

    @Autowired
    private StockRiseRateService stockRiseRateService;

   @Test
    public void main() {
        // 创建word文档,并设置纸张的大小
        Document document = new Document(PageSize.A4);
        try {

            List<CreateCompanyWorld_VO> list = stockCompanySectorService.getCreateCompanyWorldList(null , null , null , null ,"互联网信息服务");
            RtfWriter2.getInstance(document,new FileOutputStream("E:/互联网信息服务.doc"));

            document.open();

            for(CreateCompanyWorld_VO body : list){
                List<StockCompanyProduct> productList = stockCompanyProductService.getNewCompanyProductList("产品分类" , body.getStockCode());

                List<StockCompanyProduct> regionList = stockCompanyProductService.getNewCompanyProductList("地域分类" , body.getStockCode());

                createProfitDataImg( body.getStockCode());

                createProfitSeasonDataImg( body.getStockCode());

                createAssetDataImg( body.getStockCode());

                createCycleDataImg( body.getStockCode());

                document = getTableList(document , body, productList , regionList);
                FileUtils.deleteSamilarFile("E:/dataImg/" , body.getStockCode());
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Document getTableList(Document document , CreateCompanyWorld_VO body, List<StockCompanyProduct> productList , List<StockCompanyProduct> regionList){

        try {
            /*
             * 创建有三列的表格
             */
            Table table = new Table(6);
            table.setWidth(100);
            table.setBorderWidth(0);
            table.setBorderColor(Color.BLACK);
            table.setPadding(0);
            table.setSpacing(0);

            Font font = new Font();
            font.setStyle(Font.BOLD);
            font.setSize(9);

            Font fontContent = new Font();
            fontContent.setSize(9);

            Cell cell = new Cell();
            /*********************第 1 行*********************/
            Paragraph stockCode = new Paragraph("企业编码" ,font);
            cell = new Cell(stockCode);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph stockCodeContent = new Paragraph(body.getStockCode() ,fontContent);
            table.addCell(stockCodeContent);

            Paragraph compayName = new Paragraph("企业名称" ,font);
            cell = new Cell(compayName);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph compayNameContent = new Paragraph(body.getCompanyName() ,fontContent);
            table.addCell(compayNameContent);

            Paragraph compayIdentity = new Paragraph("企业性质" ,font);
            cell = new Cell(compayIdentity);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph compayIdentityContent = new Paragraph(body.getCompanyClass() ,fontContent);
            table.addCell(compayIdentityContent);

            /*********************第 2 行*********************/
            Paragraph buildTime = new Paragraph("成立时间" ,font);
            cell = new Cell(buildTime);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph buildTimeContent = new Paragraph(body.getEstablishDay() ,fontContent);
            table.addCell(buildTimeContent);

            Paragraph publishTime = new Paragraph("上市时间" ,font);
            cell = new Cell(publishTime);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph publishTimeContent = new Paragraph(body.getPublishDay() ,fontContent);
            table.addCell(publishTimeContent);

            Paragraph spaceYear = new Paragraph("历经时间" ,font);
            cell = new Cell(spaceYear);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph spaceYearContent = new Paragraph(body.getSpaceYear() ,fontContent);
            table.addCell(spaceYearContent);

            /*********************第 3 行*********************/
            Paragraph publishAmount = new Paragraph("发行股本" ,font);
            cell = new Cell(publishAmount);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph publishAmountContent = new Paragraph(body.getPublishCount() ,fontContent);
            table.addCell(publishAmountContent);

            Paragraph flowAmount = new Paragraph("流通股本" ,font);
            cell = new Cell(flowAmount);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph flowAmountContent = new Paragraph(body.getFlowCount() ,fontContent);
            table.addCell(flowAmountContent);

            Paragraph flowRate = new Paragraph("流通占比" ,font);
            cell = new Cell(flowRate);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph flowRateContent = new Paragraph(body.getFlowRate() ,fontContent);
            table.addCell(flowRateContent);

            /*********************第 4 行*********************/
            Paragraph marketOrder = new Paragraph("市场地位" ,font);
            cell = new Cell(marketOrder);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph marketOrderContent = new Paragraph(body.getMarketOrder() ,fontContent);
            table.addCell(marketOrderContent);

            Paragraph sectorOrder = new Paragraph("产业地位" ,font);
            cell = new Cell(sectorOrder);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph sectorOrderContent = new Paragraph(body.getSectorOrder() ,fontContent);
            table.addCell(sectorOrderContent);

            Paragraph companyType = new Paragraph("企业类型" ,font);
            cell = new Cell(companyType);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph companyTypeContent = new Paragraph(body.getCompanyType() ,fontContent);
            table.addCell(companyTypeContent);

            /*********************第 5 行*********************/
            Paragraph companyRegion = new Paragraph("地理位置" ,font);
            cell = new Cell(companyRegion);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph companyRegionContent = new Paragraph(body.getCompanyRegion() ,fontContent);
            table.addCell(companyRegionContent);

            Paragraph companyStrength = new Paragraph("企业特点" ,font);
            cell = new Cell(companyStrength);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph companyStrengthContent = new Paragraph(body.getCompanyStrength() ,fontContent);
            table.addCell(companyStrengthContent);

            Paragraph companyChance = new Paragraph("企业潜力" ,font);
            cell = new Cell(companyChance);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph companyChanceContent = new Paragraph(body.getCompanyChance() ,fontContent);
            table.addCell(companyChanceContent);


            /*********************第 6 行*********************/
            int rows = 0 ;
            if(productList.size() >= regionList.size()){
                rows = productList.size();
            }else{
                rows = regionList.size();
            }

            for(int i = 0 ; i < rows ; i ++){
                if(i == 0){
                    Paragraph mainProduct = new Paragraph("主营产品" ,font);
                    cell = new Cell(mainProduct);
                    cell.setRowspan(rows);
                    cell.setHorizontalAlignment(1);
                    cell.setVerticalAlignment(1);
                    table.addCell(cell);

                    StockCompanyProduct product = productList.get(i);
                    Paragraph mainProductContent = new Paragraph(product.getProductName() ,fontContent);
                    cell = new Cell(mainProductContent);
                    table.addCell(cell);

                    Paragraph mainProductRate = new Paragraph(product.getProductRevenueRate() ,fontContent);
                    cell = new Cell(mainProductRate);
                    table.addCell(cell);


                    Paragraph mainRegion = new Paragraph("主营地域" ,font);
                    cell = new Cell(mainRegion);
                    cell.setRowspan(rows);
                    cell.setHorizontalAlignment(1);
                    cell.setVerticalAlignment(1);
                    table.addCell(cell);

                    StockCompanyProduct region = regionList.get(i);
                    Paragraph mainRegionContent = new Paragraph(region.getProductName() ,fontContent);
                    cell = new Cell(mainRegionContent);
                    table.addCell(cell);

                    Paragraph mainRegionRate = new Paragraph(region.getProductRevenueRate() ,fontContent);
                    cell = new Cell(mainRegionRate);
                    table.addCell(cell);
                }else {
                    if(i < productList.size()){
                        StockCompanyProduct product = productList.get(i);
                        Paragraph mainProductContent = new Paragraph(product.getProductName() ,fontContent);
                        cell = new Cell(mainProductContent);
                        table.addCell(cell);

                        Paragraph mainProductRate = new Paragraph(product.getProductRevenueRate() ,fontContent);
                        cell = new Cell(mainProductRate);
                        table.addCell(cell);
                    }else{
                        table.addCell("");
                        table.addCell("");
                    }

                    if(i < regionList.size()){
                        StockCompanyProduct region = regionList.get(i);
                        Paragraph mainRegionContent = new Paragraph(region.getProductName() ,fontContent);
                        cell = new Cell(mainRegionContent);
                        table.addCell(cell);

                        Paragraph mainRegionRate = new Paragraph(region.getProductRevenueRate() ,fontContent);
                        cell = new Cell(mainRegionRate);
                        table.addCell(cell);
                    }else {
                        table.addCell("");
                        table.addCell("");
                    }
                }
            }


            /*********************第 7 行*********************/
            Paragraph productType = new Paragraph("产品分类" ,font);
            cell = new Cell(productType);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph productTypeContent = new Paragraph("" ,font);
            cell = new Cell(productTypeContent);
            cell.setColspan(5);
            table.addCell(cell);

            /*********************第 8 行*********************/
            Paragraph marketClass = new Paragraph("市场划份" ,font);
            cell = new Cell(marketClass);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph marketClassContent = new Paragraph(body.getMarketClass() ,fontContent);
            cell = new Cell(marketClassContent);
            cell.setColspan(2);
            table.addCell(cell);

            Paragraph internalAgree = new Paragraph("国际认同" ,font);
            cell = new Cell(internalAgree);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph internalAgreeContent = new Paragraph(body.getInternalOrganize() ,fontContent);
            cell = new Cell(internalAgreeContent);
            cell.setColspan(2);
            table.addCell(cell);

            /*********************第 9 行*********************/
            Paragraph focusOrganize = new Paragraph("关注对象" ,font);
            cell = new Cell(focusOrganize);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph focusOrganizeContent = new Paragraph(body.getFocusOrganize() ,fontContent);
            cell = new Cell(focusOrganizeContent);
            cell.setColspan(5);
            table.addCell(cell);

            /*********************第 10 行*********************/
            Paragraph productHot = new Paragraph("热点内容" ,font);
            cell = new Cell(productHot);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Paragraph productHotContent = new Paragraph(body.getGroupHot() ,fontContent);
            cell = new Cell(productHotContent);
            cell.setColspan(5);
            table.addCell(cell);

            /*********************第 11 行*********************/
            Paragraph companyProfit = new Paragraph("盈利情况" ,font);
            cell = new Cell(companyProfit);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            Image img = Image.getInstance("E:\\dataImg\\"+ body.getStockCode() +"_profit.jpg");
            img.setAbsolutePosition(0, 0);
            img.setAlignment(Image.LEFT);
            img.scalePercent(42);
            cell = new Cell(img);
            cell.setColspan(5);
            table.addCell(cell);

            img = Image.getInstance("E:\\dataImg\\"+ body.getStockCode() +"_season_profit.jpg");
            img.setAbsolutePosition(0, 0);
            img.setAlignment(Image.LEFT);
            img.scalePercent(42);
            cell = new Cell(img);
            cell.setColspan(5);
            table.addCell(cell);

            /*********************第 12 行*********************/
            Paragraph companyAsset = new Paragraph("资产情况" ,font);
            cell = new Cell(companyAsset);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            img = Image.getInstance("E:\\dataImg\\"+ body.getStockCode() +"_asset.jpg");
            img.setAbsolutePosition(0, 0);
            img.setAlignment(Image.LEFT);
            img.scalePercent(42);
            cell = new Cell(img);
            cell.setColspan(5);
            table.addCell(cell);

            /*********************第 13 行*********************/
            Paragraph marketCycle = new Paragraph("市场周期" ,font);
            cell = new Cell(marketCycle);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            table.addCell(cell);

            img = Image.getInstance("E:\\dataImg\\"+ body.getStockCode() +"_rate.jpg");
            img.setAbsolutePosition(0, 0);
            img.setAlignment(Image.LEFT);
            img.scalePercent(42);
            cell = new Cell(img);
            cell.setColspan(5);
            table.addCell(cell);

            document.add(table);
        }catch (Exception e){
            e.printStackTrace();
        }

        return document;
    }


    private void createProfitDataImg(String stockCode){
        try {
            List<StockCompanyProfitVO> list = stockCompanyProfitService.getCompanyProfitGrowList(stockCode);
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

            // 2：创建Chart[创建不同图形]
            JFreeChart chart = ChartFactory.createLineChart("利润增长", "", "收益（亿）", dataset);
            // 3:设置抗锯齿，防止字体显示不清楚
            ChartUtils.setAntiAlias(chart);// 抗锯齿
            // 4:对柱子进行渲染[[采用不同渲染]]
            ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
            // 5:对其他部分进行渲染
            ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
            ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

            ChartUtils.saveAsFile(chart , "E:/dataImg/"+ stockCode +"_profit.jpg" , 1024 , 420);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createProfitSeasonDataImg(String stockCode){
        try {
            List<StockCompanyProfitVO> list = stockCompanyProfitService.getCompanyProfitGrowList(stockCode);
            List<String> title = new ArrayList<>();

            List<BigDecimal> firstProfitRate = new ArrayList<>();
            List<BigDecimal> secondProfitRate = new ArrayList<>();
            List<BigDecimal> threeProfitRate = new ArrayList<>();
            List<BigDecimal> forthtProfitRate = new ArrayList<>();

            for(StockCompanyProfitVO profit : list){
                title.add(profit.getPublishYear());
                firstProfitRate.add(profit.getFirstProfitRate());
                secondProfitRate.add(profit.getSecondProfitRate());
                threeProfitRate.add(profit.getThreeProfitRate());
                forthtProfitRate.add(profit.getForthtProfitRate());
            }
            String[] categories = new String[title.size()];
            title.toArray(categories);
            Vector<Serie> series = new Vector<Serie>();
            // 柱子名称：柱子所有的值集合
            series.add(new Serie("第一季度", firstProfitRate));
            series.add(new Serie("第二季度", secondProfitRate));
            series.add(new Serie("第三季度", threeProfitRate));
            series.add(new Serie("第四季度", forthtProfitRate));
            // 1：创建数据集合
            DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);

            // 2：创建Chart[创建不同图形]
            JFreeChart chart = ChartFactory.createLineChart("季度占比", "", "比例（%）", dataset);
            // 3:设置抗锯齿，防止字体显示不清楚
            ChartUtils.setAntiAlias(chart);// 抗锯齿
            // 4:对柱子进行渲染[[采用不同渲染]]
            ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
            // 5:对其他部分进行渲染
            ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
            ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

            ChartUtils.saveAsFile(chart , "E:/dataImg/"+ stockCode +"_season_profit.jpg" , 1024 , 420);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createAssetDataImg(String stockCode){
        try {
            List<StockCompanyAssetVO> list = stockCompanyAssetService.getCompanyAssetGrowList(stockCode);
            List<String> title = new ArrayList<>();

            List<BigDecimal> totalAsset = new ArrayList<>();
            List<BigDecimal> totalDebt = new ArrayList<>();
            List<BigDecimal> pureAsset = new ArrayList<>();

            for(StockCompanyAssetVO asset : list){
                title.add(asset.getPublishYear());
                totalAsset.add(asset.getTotalAsset());
                totalDebt.add(asset.getTotalDebt());
                pureAsset.add(asset.getPureAsset());
            }
            String[] categories = new String[title.size()];
            title.toArray(categories);
            Vector<Serie> series = new Vector<Serie>();
            // 柱子名称：柱子所有的值集合
            series.add(new Serie("总资产", totalAsset));
            series.add(new Serie("净资产", pureAsset));
            series.add(new Serie("总负债", totalDebt));
            // 1：创建数据集合
            DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);

            // 2：创建Chart[创建不同图形]
            JFreeChart chart = ChartFactory.createLineChart("资产增长", "", "收益（亿）", dataset);
            // 3:设置抗锯齿，防止字体显示不清楚
            ChartUtils.setAntiAlias(chart);// 抗锯齿
            // 4:对柱子进行渲染[[采用不同渲染]]
            ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
            // 5:对其他部分进行渲染
            ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
            ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

            ChartUtils.saveAsFile(chart , "E:/dataImg/"+ stockCode +"_asset.jpg" , 1024 , 420);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCycleDataImg(String stockCode){
        try {
            StockRiseRate riseRate = stockRiseRateService.selectOne(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and start_time = '2015-01-01' and deal_period = 3" , stockCode ));
            Map<String ,JSONArray> map = new HashMap<>();
            List<BigDecimal> rateArr = new ArrayList<>();
            List<BigDecimal> upperArr = new ArrayList<>();
            List<BigDecimal> shockArr = new ArrayList<>();
            rateArr.add(riseRate.getOneRise());
            rateArr.add(riseRate.getTowRise());
            rateArr.add(riseRate.getThreeRise());
            rateArr.add(riseRate.getFourRise());
            rateArr.add(riseRate.getFiveRise());
            rateArr.add(riseRate.getSixRise());
            rateArr.add(riseRate.getSevenRise());
            rateArr.add(riseRate.getEightRise());
            rateArr.add(riseRate.getNineRise());
            rateArr.add(riseRate.getTenRise());
            rateArr.add(riseRate.getElevenRise());
            rateArr.add(riseRate.getTwelveRise());

            upperArr.add(riseRate.getOneAmplitude());
            upperArr.add(riseRate.getTowAmplitude());
            upperArr.add(riseRate.getThreeAmplitude());
            upperArr.add(riseRate.getFourAmplitude());
            upperArr.add(riseRate.getFiveAmplitude());
            upperArr.add(riseRate.getSixAmplitude());
            upperArr.add(riseRate.getSevenAmplitude());
            upperArr.add(riseRate.getEightAmplitude());
            upperArr.add(riseRate.getNineAmplitude());
            upperArr.add(riseRate.getTenAmplitude());
            upperArr.add(riseRate.getElevenAmplitude());
            upperArr.add(riseRate.getTwelveAmplitude());

            shockArr.add(riseRate.getOneShock());
            shockArr.add(riseRate.getTowShock());
            shockArr.add(riseRate.getThreeShock());
            shockArr.add(riseRate.getFourShock());
            shockArr.add(riseRate.getFiveShock());
            shockArr.add(riseRate.getSixShock());
            shockArr.add(riseRate.getSevenShock());
            shockArr.add(riseRate.getEightShock());
            shockArr.add(riseRate.getNineShock());
            shockArr.add(riseRate.getTenShock());
            shockArr.add(riseRate.getElevenShock());
            shockArr.add(riseRate.getTwelveShock());

            String[] categories = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
            Vector<Serie> series = new Vector<Serie>();
            // 柱子名称：柱子所有的值集合
            series.add(new Serie("涨幅", upperArr));
            series.add(new Serie("涨率", rateArr));
            series.add(new Serie("振幅", shockArr));
            // 1：创建数据集合
            DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);

            // 2：创建Chart[创建不同图形]
            JFreeChart chart = ChartFactory.createBarChart("资产增长", "", "收益（亿）", dataset);
            // 3:设置抗锯齿，防止字体显示不清楚
            ChartUtils.setAntiAlias(chart);// 抗锯齿
            // 4:对柱子进行渲染[[采用不同渲染]]
//            ChartUtils.setLineRender(chart.getCategoryPlot(), true,true);
            // 5:对其他部分进行渲染
            ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
            ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

            ChartUtils.saveAsFile(chart , "E:/dataImg/"+ stockCode +"_rate.jpg" , 1024 , 420);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
