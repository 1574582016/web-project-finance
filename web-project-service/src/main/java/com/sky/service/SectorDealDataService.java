package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SectorDealData;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface SectorDealDataService extends IService<SectorDealData> {

    List<SectorDealData> spiderSectorDealData(Integer periodType , String sectorCode);
}
