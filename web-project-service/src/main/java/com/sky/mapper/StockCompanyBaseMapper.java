package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockCompanyBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCompanyBaseMapper extends BaseMapper<StockCompanyBase> {

    List<StockCompanyBase> getStockCompanyBaseList(Pagination page, @Param("stockCode") String stockCode,
                                                                    @Param("stockName") String stockName,
                                                                    @Param("stockSector") String stockSector,
                                                                    @Param("stockExchange") String stockExchange ,
                                                                    @Param("startDay")  String startDay ,
                                                                    @Param("endDay") String endDay);
}
