package com.sky.test;

import com.sky.core.utils.MyDBUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2020/3/2.
 */
public class Testpoi2 {

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

                for(int i = firstRowIndex; i <= lastRowIndex; i ++) {   //遍历行

                    if (i > 0) {
                        Row rowColume = sheet.getRow(0);
                        Row rowValue = sheet.getRow(i);
                        if (rowValue != null) {
                            String sql = "update "  + tableName + " set ";
                            String where = " where ";
                            int firstCellIndex = rowValue.getFirstCellNum();
                            int lastCellIndex = rowValue.getLastCellNum();
                            for (int j = firstCellIndex; j < lastCellIndex; j++) {   //遍历列
                                Cell cellColume = rowColume.getCell(j);
                                Cell cellValue = rowValue.getCell(j);
                                if(j > firstCellIndex && j < lastCellIndex - 1){
                                    sql += cellColume.toString() + " = '" + cellValue.toString() + "' , ";
                                }
                                if(j == firstCellIndex){
                                    where += cellColume.toString() + " = '" + cellValue.toString().replace(".0","") + "';";
                                }
                                if(j == lastCellIndex - 1){
                                    sql += cellColume.toString() + " = '" + cellValue.toString() + "' ";
                                }

                            }
                            System.out.println(sql + where);
                            MyDBUtils.update(sql + where);
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
