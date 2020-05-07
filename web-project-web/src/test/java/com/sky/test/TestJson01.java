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
        String url = "http://webf10.gw.com.cn/BK/B4/SH994247_B4.html";
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


        String str = "";

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
