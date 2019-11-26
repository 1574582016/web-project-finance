package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.SectorRiseRate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface SectorRiseRateMapper extends BaseMapper<SectorRiseRate> {

    List<SectorRiseRate> getSectorCycleMonthList(@Param("sectorName") String sectorName ,@Param("startDay") String startDay ,@Param("sectorLevel") String sectorLevel);
}
