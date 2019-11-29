package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockRiseRate;
import com.sky.vo.PointMonthStock_VO;
import com.sky.vo.StaticSectorNum_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface StockRiseRateService extends IService<StockRiseRate> {

    List<StaticSectorNum_VO> getStaticSectorNum(String staticDate ,
                                                String staticMonth ,
                                                String staticRate ,
                                                String staticAmplitude ,
                                                String sectorType ,
                                                String firstSector ,
                                                String secondSector ,
                                                String thirdSecotor );

    Page<PointMonthStock_VO> getPointMonthStockList(Integer page , Integer size ,
                                                    String staticDate ,
                                                    String staticMonth ,
                                                    String staticRate ,
                                                    String staticAmplitude ,
                                                    String firstSector,
                                                    String secondSector,
                                                    String thirdSecotor,
                                                    String forthSector);

    Page<PointMonthStock_VO> getPointWeekStockList(Integer page , Integer size ,
                                                    String staticDate ,
                                                    String staticMonth ,
                                                    String staticWeek ,
                                                    String staticRate ,
                                                    String staticAmplitude );
}
