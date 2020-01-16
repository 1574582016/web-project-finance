package com.sky.test;

import com.sky.core.utils.ExcelToSqlUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2020/1/16.
 */
public class Test07 {

    public static void main(String[] args) {
        String excelPath = "D:\\test.xlsx";
        List<Map<String ,String>> list = ExcelToSqlUtils.saveExcelToSql(excelPath);
        System.out.println(list.toString());
    }
}
