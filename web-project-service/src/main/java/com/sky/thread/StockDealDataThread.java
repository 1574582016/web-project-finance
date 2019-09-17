package com.sky.thread;

import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public class StockDealDataThread extends Thread {

    private List<String> list;
    private Map<Long,Integer> map;

    public StockDealDataThread(List<String> list,Map<Long,Integer> map){
        this.list = list;
        this.map = map;
    }


    @Override
    public void run() {
        int pcount = Runtime.getRuntime().availableProcessors();
        int i = map.get(Thread.currentThread().getId());
        for(;i<list.size();i+=pcount){
            System.out.println(list.get(i));
        }

    }
}
