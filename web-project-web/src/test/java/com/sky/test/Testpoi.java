package com.sky.test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sky.core.utils.MyDBUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testpoi {

    public static void main(String[] args) {
        String excelPath = "E:\\ttt.xls";
        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                String tableName = sheet.getSheetName() ;

                int firstRowIndex = sheet.getFirstRowNum();   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();

                String sql = "" ;
                String sql0 = "";
                String sql1 = "";
                Map<String ,String> map = new HashMap<>();
                for(int i = firstRowIndex; i <= lastRowIndex; i ++) {   //遍历行

                    if(i > 0){
                        Row rowColume = sheet.getRow(0);
                        Row rowValue = sheet.getRow(i);
                        if (rowValue != null) {
                            System.out.println("====================" + rowValue.toString());
                            int firstCellIndex = rowValue.getFirstCellNum();
                            int lastCellIndex = rowValue.getLastCellNum();
                            for (int j = firstCellIndex; j < lastCellIndex; j++) {   //遍历列
                                Cell cellColume = rowColume.getCell(j);
                                Cell cellValue = rowValue.getCell(j);
                                if (cellValue != null) {
                                    System.out.println(cellColume.toString() + "=======================" + cellValue.toString());
                                    map.put(cellColume.toString() , cellValue.toString());
                                }
                            }
                        }
                    }

                    if( i > 0){
                        String sql2 = getQuerySql(wb ,map);

                        List<String> list = MyDBUtils.executeQuerySql(sql2);
                        if(list.size() == 0){


                            Row row = sheet.getRow(i);
                            if (row != null) {
                                int firstCellIndex = row.getFirstCellNum();
                                int lastCellIndex = row.getLastCellNum();
                                sql0 += " ( " ;
                                for (int j = firstCellIndex; j < lastCellIndex; j++) {   //遍历列
                                    Cell cell = row.getCell(j);
                                    if (cell != null) {
                                        if(i == 0){
                                            if(j < lastCellIndex - 1){
                                                sql0 += cell.toString() + " , " ;
                                            }else{
                                                sql0 += cell.toString() ;
                                            }
                                        }else{
                                            if(j < lastCellIndex - 1){
                                                sql0 += "'" + cell.toString() + "' , " ;
                                            }else{
                                                sql0 += "'" + cell.toString() + "' " ;
                                            }
                                        }
                                    }
                                }
                                if(i == 0){
                                    sql0 += ") values";
                                }

                                if(i > 0 && i < lastRowIndex) {
                                    sql0 += ") ,";
                                }

                                if(i == lastRowIndex){
                                    sql0 += ") ; ";
                                }
                            }
                        }
                    }else{
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            int firstCellIndex = row.getFirstCellNum();
                            int lastCellIndex = row.getLastCellNum();
                            sql1 += " ( " ;
                            for (int j = firstCellIndex; j < lastCellIndex; j++) {   //遍历列
                                Cell cell = row.getCell(j);
                                if (cell != null) {
                                    if(i == 0){
                                        if(j < lastCellIndex - 1){
                                            sql1 += cell.toString() + " , " ;
                                        }else{
                                            sql1 += cell.toString() ;
                                        }
                                    }else{
                                        if(j < lastCellIndex - 1){
                                            sql1 += "'" + cell.toString() + "' , " ;
                                        }else{
                                            sql1 += "'" + cell.toString() + "' " ;
                                        }
                                    }
                                }
                            }
                            if(i == 0){
                                sql1 += ") values";
                            }

                            if(i > 0 && i < lastRowIndex) {
                                sql1 += ") ,";
                            }

                            if(i == lastRowIndex){
                                sql1 += ") ; ";
                            }
                        }
                    }
                }

                if(StringUtils.isNotBlank(sql0)){
                    sql = "insert into " + tableName + sql1 + sql0;
                    MyDBUtils.update(sql);
                }
                System.out.println(sql);
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getQuerySql(Workbook wb , Map<String ,String> map){
        Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
        String tableName = sheet.getSheetName() ;

        //开始解析
        Sheet sheet2 = wb.getSheetAt(1);     //读取sheet 1

        String sql2 = "SELECT " ;
        Row row2 = sheet2.getRow(0);
        if (row2 != null) {
            int firstCellIndex = row2.getFirstCellNum();
            int lastCellIndex = row2.getLastCellNum();
            for (int j = firstCellIndex; j < lastCellIndex; j++) {   //遍历列
                Cell cell = row2.getCell(j);
                if (cell != null) {
                    if(j == 0){
                        sql2 +=  cell.toString()  + " from " + tableName + " where " + cell.toString() + " = '"+ map.get(cell.toString()) +"' ";
                    }else{
                        sql2 += " and " + cell.toString() + " = '"+ map.get(cell.toString()) +"' ";
                    }
                }
            }
        }

        return sql2 ;
    }

}
