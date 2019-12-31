package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockProfitAvaregeRate;
import com.sky.vo.StockProfitAvaregeRate_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/30.
 */
public interface StockProfitAvaregeRateMapper extends BaseMapper<StockProfitAvaregeRate> {

    List<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(Pagination page ,
                                                                  @Param("stockCode") String stockCode ,
                                                                  @Param("stockName") String stockName ,
                                                                  @Param("firstSector") String firstSector ,
                                                                  @Param("secondSector") String secondSector ,
                                                                  @Param("thirdSecotor") String thirdSecotor ,
                                                                  @Param("forthSector") String forthSector ,
                                                                  @Param("yearType") String yearType);
}
