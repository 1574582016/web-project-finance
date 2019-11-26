package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SectorRiseRate;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface SectorRiseRateService extends IService<SectorRiseRate> {

    List<SectorRiseRate> getSectorCycleList(String startDay ,
                                            String sectorLevel ,
                                            String dealPeriod ,
                                            String firstSector ,
                                            String secondSector ,
                                            String thirdSecotor ,
                                            String forthSector);
}
