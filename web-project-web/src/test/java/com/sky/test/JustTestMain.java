package com.sky.test;

/**
 * Created by Administrator on 2019/12/25/025.
 */
public class JustTestMain {

    public static void main(String[] args){
        int max=100000000,min=1;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum%(max-min)+min);
        System.out.println(ran3);
    }
}
