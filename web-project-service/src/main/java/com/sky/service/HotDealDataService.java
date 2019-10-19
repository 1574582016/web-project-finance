package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.HotDealData;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/19.
 */
public interface HotDealDataService extends IService<HotDealData> {

    List<HotDealData> spiderHotDealData(Integer periodType , String sectorCode);
}
