package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockInvestorProduct;
import com.sky.vo.StockInvestor_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/24.
 */
public interface StockInvestorProductMapper extends BaseMapper<StockInvestorProduct> {

    List<StockInvestor_VO> getStockInvestorStaticList(@Param("stockCode") String stockCode);

    List<StockInvestor_VO> getStockInvestorList(@Param("stockCode") String stockCode);
}
