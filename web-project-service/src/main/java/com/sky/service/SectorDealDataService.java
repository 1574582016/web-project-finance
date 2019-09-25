package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SectorDealData;
import com.sky.vo.CovarDeal_VO;
import com.sky.vo.CovarStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface SectorDealDataService extends IService<SectorDealData> {

    List<SectorDealData> spiderSectorDealData(Integer periodType , String sectorCode);

    List<CovarDeal_VO> getSectorDealCovarList(String sectorCode , String dealPeriod , String startDay ,String endDay );


    List<CovarStatic_VO> caculateCovarIndexAndSector(String indexCode , String dealPeriod , String startDay ,String endDay );
}
