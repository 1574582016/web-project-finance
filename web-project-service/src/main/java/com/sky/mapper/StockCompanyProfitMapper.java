package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyProfit;
import com.sky.vo.StockCompanyProfitVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
public interface StockCompanyProfitMapper extends BaseMapper<StockCompanyProfit> {

    List<StockCompanyProfitVO> getCompanyProfitGrowList(@Param("stockCode") String stockCode);
}
