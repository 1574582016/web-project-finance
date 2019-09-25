package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockDealData;
import com.sky.vo.CovarDeal_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface SectorDealDataMapper extends BaseMapper<SectorDealData> {

    List<CovarDeal_VO> getSectorDealCovarList(@Param("sectorCode") String sectorCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay );
}
