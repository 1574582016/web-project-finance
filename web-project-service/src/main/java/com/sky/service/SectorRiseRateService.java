package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SectorRiseRate;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface SectorRiseRateService extends IService<SectorRiseRate> {

    List<SectorRiseRate> getSectorCycleMonthList(String sectorName ,String startDay ,String sectorLevel);
}
