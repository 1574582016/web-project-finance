package com.sky.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/12/25/025.
 */
public class JustTestMain {

    public static void main(String[] args){
//        int max=100000000,min=1;
//        long randomNum = System.currentTimeMillis();
//        int ran3 = (int) (randomNum%(max-min)+min);
//        System.out.println(ran3);

//        String sameRoot = "brev,brevi,brief,bridg";
//        String str = getDifferentRoot(sameRoot);
//        System.out.println(str);

        for(int x = 100 ; x >= 0 ; x--){
            BigDecimal max = BigDecimal.valueOf(140).subtract((BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(x)))).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal min = BigDecimal.valueOf(30).subtract((BigDecimal.valueOf(0.1).multiply(BigDecimal.valueOf(x)))).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal rate = max.divide(min ,2 , BigDecimal.ROUND_HALF_UP);
            System.out.println("========"+ x +"=====max====" + max + "=====min====" + min + "=====rate====" + rate);
            System.out.println();
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
