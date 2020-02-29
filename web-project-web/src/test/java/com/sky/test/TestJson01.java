package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.SpiderUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2020/2/28.
 */
public class TestJson01 {



    public static void main(String[] args){
        String url = "http://webf10.gw.com.cn/BK/B4/SH991256_B4.html";
        Document document = SpiderUtils.HtmlJsoupGet(url);
        Elements table = document.getElementsByClass("f10tabel_new");
        List<JSONObject> list2 = new ArrayList<>();
        if(table.size() > 0) {
            try{
                Elements elements = table.get(0).getElementsByTag("tr");
                for (int i = 1; i < elements.size(); i++) {
                    Elements element2 = elements.get(i).getElementsByTag("td");
                    JSONObject marketStock = new JSONObject();
                    for (int j = 1; j < element2.size(); j++) {
                        Element element = element2.get(j);
                        switch (j) {
                            case 1:
                                marketStock.put("stockCode" ,element.text());
                                break;
                            case 4:
                                marketStock.put("stockDescribe" , element.text());
                                break;
                        }
                    }
                    list2.add(marketStock);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        String str = "[{\"dm\":\"600053\",\"gpdm\":\"SH600053\",\"jlr\":\"2.26\",\"jlrtb\":\"132920.59\",\"jzc\":\"4.76\",\"ltg\":\"4.34\",\"mc\":\"九鼎投资\",\"sr\":\"5.72\",\"srtb\":\"-4.21\",\"sy\":\"0.5206\",\"syl\":\"10.39\",\"zg\":\"4.34\"},{\"dm\":\"600120\",\"gpdm\":\"SH600120\",\"jlr\":\"6.14\",\"jlrtb\":\"8.64\",\"jzc\":\"6.80\",\"ltg\":\"12.11\",\"mc\":\"浙江东方\",\"sr\":\"89.17\",\"srtb\":\"32.54\",\"sy\":\"0.3859\",\"syl\":\"6.10\",\"zg\":\"15.91\"},{\"dm\":\"600318\",\"gpdm\":\"SH600318\",\"jlr\":\"0.39\",\"jlrtb\":\"1.80\",\"jzc\":\"2.49\",\"ltg\":\"4.84\",\"mc\":\"新力金融\",\"sr\":\"3.78\",\"srtb\":\"-3.17\",\"sy\":\"0.0754\",\"syl\":\"3.38\",\"zg\":\"5.13\"},{\"dm\":\"600390\",\"gpdm\":\"SH600390\",\"jlr\":\"26.55\",\"jlrtb\":\"56.59\",\"jzc\":\"7.65\",\"ltg\":\"44.98\",\"mc\":\"五矿资本\",\"sr\":\"122.01\",\"srtb\":\"33.50\",\"sy\":\"0.5904\",\"syl\":\"7.85\",\"zg\":\"44.98\"},{\"dm\":\"600599\",\"gpdm\":\"SH600599\",\"jlr\":\"-0.19\",\"jlrtb\":\"29.42\",\"jzc\":\"3.94\",\"ltg\":\"1.66\",\"mc\":\"熊猫金控\",\"sr\":\"1.04\",\"srtb\":\"-52.67\",\"sy\":\"-0.1120\",\"syl\":\"-2.81\",\"zg\":\"1.66\"},{\"dm\":\"600643\",\"gpdm\":\"SH600643\",\"jlr\":\"10.21\",\"jlrtb\":\"25.04\",\"jzc\":\"6.47\",\"ltg\":\"14.34\",\"mc\":\"爱建集团\",\"sr\":\"27.80\",\"srtb\":\"53.08\",\"sy\":\"0.6297\",\"syl\":\"10.15\",\"zg\":\"16.22\"},{\"dm\":\"600705\",\"gpdm\":\"SH600705\",\"jlr\":\"21.02\",\"jlrtb\":\"-4.46\",\"jzc\":\"3.21\",\"ltg\":\"89.76\",\"mc\":\"中航资本\",\"sr\":\"126.91\",\"srtb\":\"26.12\",\"sy\":\"0.2342\",\"syl\":\"7.44\",\"zg\":\"89.76\"},{\"dm\":\"600816\",\"gpdm\":\"SH600816\",\"jlr\":\"-3.45\",\"jlrtb\":\"-1533.59\",\"jzc\":\"2.22\",\"ltg\":\"52.64\",\"mc\":\"安信信托\",\"sr\":\"5.29\",\"srtb\":\"-80.48\",\"sy\":\"-0.0631\",\"syl\":\"-2.80\",\"zg\":\"54.69\"},{\"dm\":\"600830\",\"gpdm\":\"SH600830\",\"jlr\":\"0.21\",\"jlrtb\":\"-27.68\",\"jzc\":\"4.58\",\"ltg\":\"4.54\",\"mc\":\"香溢融通\",\"sr\":\"3.42\",\"srtb\":\"-54.99\",\"sy\":\"0.0472\",\"syl\":\"1.10\",\"zg\":\"4.54\"},{\"dm\":\"603300\",\"gpdm\":\"SH603300\",\"jlr\":\"2.41\",\"jlrtb\":\"201.09\",\"jzc\":\"2.53\",\"ltg\":\"6.36\",\"mc\":\"华铁应急\",\"sr\":\"8.18\",\"srtb\":\"23.71\",\"sy\":\"0.3542\",\"syl\":\"15.08\",\"zg\":\"6.80\"},{\"dm\":\"000046\",\"gpdm\":\"SZ000046\",\"jlr\":\"24.94\",\"jlrtb\":\"37.23\",\"jzc\":\"4.41\",\"ltg\":\"51.75\",\"mc\":\"泛海控股\",\"sr\":\"82.97\",\"srtb\":\"2.74\",\"sy\":\"0.4799\",\"syl\":\"11.77\",\"zg\":\"51.96\"},{\"dm\":\"000415\",\"gpdm\":\"SZ000415\",\"jlr\":\"20.81\",\"jlrtb\":\"-14.55\",\"jzc\":\"6.21\",\"ltg\":\"35.47\",\"mc\":\"渤海租赁\",\"sr\":\"262.63\",\"srtb\":\"-10.57\",\"sy\":\"0.3365\",\"syl\":\"5.58\",\"zg\":\"61.85\"},{\"dm\":\"000416\",\"gpdm\":\"SZ000416\",\"jlr\":\"0.30\",\"jlrtb\":\"88.50\",\"jzc\":\"1.66\",\"ltg\":\"5.32\",\"mc\":\"民生控股\",\"sr\":\"0.61\",\"srtb\":\"2.91\",\"sy\":\"0.0558\",\"syl\":\"3.38\",\"zg\":\"5.32\"},{\"dm\":\"000563\",\"gpdm\":\"SZ000563\",\"jlr\":\"4.59\",\"jlrtb\":\"94.32\",\"jzc\":\"2.71\",\"ltg\":\"39.64\",\"mc\":\"陕国投A\",\"sr\":\"12.95\",\"srtb\":\"90.78\",\"sy\":\"0.1158\",\"syl\":\"4.36\",\"zg\":\"39.64\"},{\"dm\":\"000567\",\"gpdm\":\"SZ000567\",\"jlr\":\"1.53\",\"jlrtb\":\"100.72\",\"jzc\":\"6.66\",\"ltg\":\"2.18\",\"mc\":\"海德股份\",\"sr\":\"2.96\",\"srtb\":\"19.46\",\"sy\":\"0.2390\",\"syl\":\"3.64\",\"zg\":\"6.41\"},{\"dm\":\"002423\",\"gpdm\":\"SZ002423\",\"jlr\":\"6.90\",\"jlrtb\":\"-5.10\",\"jzc\":\"7.06\",\"ltg\":\"5.03\",\"mc\":\"中粮资本\",\"sr\":\"84.35\",\"srtb\":\"13.40\",\"sy\":\"0.2997\",\"syl\":\"4.34\",\"zg\":\"23.04\"},{\"dm\":\"002647\",\"gpdm\":\"SZ002647\",\"jlr\":\"0.49\",\"jlrtb\":\"65.67\",\"jzc\":\"1.80\",\"ltg\":\"5.60\",\"mc\":\"仁东控股\",\"sr\":\"9.24\",\"srtb\":\"-7.25\",\"sy\":\"0.0880\",\"syl\":\"4.97\",\"zg\":\"5.60\"},{\"dm\":\"300023\",\"gpdm\":\"SZ300023\",\"jlr\":\"-1.91\",\"jlrtb\":\"-939.10\",\"jzc\":\"0.98\",\"ltg\":\"1.44\",\"mc\":\"宝德股份\",\"sr\":\"1.21\",\"srtb\":\"-64.45\",\"sy\":\"-0.6044\",\"syl\":\"-47.32\",\"zg\":\"3.16\"}]";


        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("用户表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("编码");
        cell = row.createCell(1);
        cell.setCellValue("名称");

        cell = row.createCell(2);
        cell.setCellValue("主营收入");
        cell = row.createCell(3);
        cell.setCellValue("净利润");
        cell = row.createCell(4);
        cell.setCellValue("每股收益");
        cell = row.createCell(5);
        cell.setCellValue("流通股");
        cell = row.createCell(6);
        cell.setCellValue("总股本");
        cell = row.createCell(7);
        cell.setCellValue("简介");

        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        JSONArray jsonArray = JSON.parseArray(str);
        for(int i = 0 ; i < jsonArray.size() ; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(jsonObject.getString("dm"));
            row1.createCell(1).setCellValue(jsonObject.getString("mc"));
            row1.createCell(2).setCellValue(jsonObject.getString("sr"));
            row1.createCell(3).setCellValue(jsonObject.getString("jlr"));
            row1.createCell(4).setCellValue(jsonObject.getString("sy"));
            row1.createCell(5).setCellValue(jsonObject.getString("ltg"));
            row1.createCell(6).setCellValue(jsonObject.getString("zg"));

            for(JSONObject market : list2){
               if(jsonObject.get("dm").equals(market.getString("stockCode"))){
                   row1.createCell(7).setCellValue(market.getString("stockDescribe"));
               }
            }
        }

        File file = new File("E:/其他金融.xls");
        if (file.exists()) {
            file.delete();
        }
        //将文件保存到指定的位置
        try {
            file.createNewFile();
            workbook.write(file);
            System.out.println("写入成功2");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
