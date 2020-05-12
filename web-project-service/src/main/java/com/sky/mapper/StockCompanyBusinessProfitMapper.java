package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyBusinessProfit;
import org.apache.ibatis.annotations.Param;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by ThinkPad on 2020/5/12.
 */
public interface StockCompanyBusinessProfitMapper extends BaseMapper<StockCompanyBusinessProfit> {

    List<StockCompanyBusinessProfit> getStockCompaneyProfitList(@Param("stockCode") String stockCode);
}
