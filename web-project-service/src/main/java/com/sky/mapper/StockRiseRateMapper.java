package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockRiseRate;
import com.sky.vo.PointMonthStock_VO;
import com.sky.vo.StaticSectorNum_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface StockRiseRateMapper extends BaseMapper<StockRiseRate> {

    List<StaticSectorNum_VO> getStaticSectorNum(@Param("staticDate") String staticDate ,
                                                @Param("staticMonth") String staticMonth ,
                                                @Param("staticRate") String staticRate ,
                                                @Param("staticAmplitude") String staticAmplitude ,
                                                @Param("sectorType") String sectorType ,
                                                @Param("firstSector") String firstSector ,
                                                @Param("secondSector") String secondSector ,
                                                @Param("thirdSecotor") String thirdSecotor );

    List<PointMonthStock_VO> getPointMonthStockList(Pagination page ,
                                                    @Param("staticDate") String staticDate ,
                                                    @Param("staticMonth") String staticMonth ,
                                                    @Param("staticRate") String staticRate ,
                                                    @Param("staticAmplitude") String staticAmplitude ,
                                                    @Param("firstSector") String firstSector,
                                                    @Param("secondSector") String secondSector,
                                                    @Param("thirdSecotor") String thirdSecotor,
                                                    @Param("forthSector") String forthSector);

    List<PointMonthStock_VO> getPointWeekStockList(Pagination page ,
                                                    @Param("staticDate") String staticDate ,
                                                    @Param("staticMonth") String staticMonth ,
                                                    @Param("staticWeek") String staticWeek ,
                                                    @Param("staticRate") String staticRate ,
                                                    @Param("staticAmplitude") String staticAmplitude);
}
