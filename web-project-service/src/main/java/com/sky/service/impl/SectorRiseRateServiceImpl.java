package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.SectorRiseRateMapper;
import com.sky.model.SectorRiseRate;
import com.sky.service.SectorRiseRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class SectorRiseRateServiceImpl extends ServiceImpl<SectorRiseRateMapper,SectorRiseRate> implements SectorRiseRateService {

    @Override
    public List<SectorRiseRate> getSectorCycleList(String startDay, String sectorLevel, String dealPeriod, String firstSector, String secondSector, String thirdSecotor, String forthSector) {
        return baseMapper.getSectorCycleList( startDay,  sectorLevel,  dealPeriod,  firstSector,  secondSector,  thirdSecotor,  forthSector);
    }
}
