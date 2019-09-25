package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockTigerList;
import com.sky.vo.StockTigerList_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/24.
 */
public interface StockTigerListMapper extends BaseMapper<StockTigerList> {


    List<StockTigerList_VO> getStockTigerList(Pagination page,
                                              @Param("stockCode") String stockCode,
                                              @Param("stockName") String stockName,
                                              @Param("startDay") String startDay,
                                              @Param("endDay") String endDay);
}
