package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.FileUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockMoneyFlow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2019/12/25/025.
 */
public class JustTestMain {

    public static void main(String[] args){
        String text = FileUtils.readFileContent("E://XAUUSD.txt");
        String[] strs = text.split("-");
        for(String str : strs){
            System.out.println(str);
        }


    }

    public static String getDifferentRoot(String sameRoot){
        Set<String> list = new HashSet<>();
        String[] roots = sameRoot.split(",");
        for(String root: roots){
            boolean just = false ;
            for(String roote : roots){
                if(!root.equals(roote) && ( root.indexOf(roote) != -1 || roote.indexOf(root) !=  -1 )){
                    just = true ;
                    System.out.println(roote);
                    if(root.indexOf(roote) != 0){
                        list.add(root);
                    }
                    if(roote.indexOf(root) != 0){
                        list.add(roote);
                    }
                }
            }
            if(!just){
                list.add(root);
            }
        }
        String str = "";
        for (String root : list) {
            str += root + ",";
        }

        return str;
    }
}
