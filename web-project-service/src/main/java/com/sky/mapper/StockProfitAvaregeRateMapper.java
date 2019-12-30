package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockProfitAvaregeRate;
import com.sky.vo.StockProfitAvaregeRate_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/30.
 */
public interface StockProfitAvaregeRateMapper extends BaseMapper<StockProfitAvaregeRate> {

    List<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(@Param("yearType") String yearType);
}
