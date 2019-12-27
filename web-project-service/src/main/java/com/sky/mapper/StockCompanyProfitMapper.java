package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyProfit;
import com.sky.vo.CompanyProfit_VO;
import com.sky.vo.StockCompanyProfitVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
public interface StockCompanyProfitMapper extends BaseMapper<StockCompanyProfit> {

    List<StockCompanyProfitVO> getCompanyProfitGrowList(@Param("stockCode") String stockCode);

    List<CompanyProfit_VO> getRecentCompanyProfitList(@Param("stockCode") String stockCode ,@Param("startYear") String startYear ,@Param("endYear") String endYear);

    CompanyProfit_VO getMaxAndMinCompanyProfit(@Param("stockCode") String stockCode ,@Param("startYear") String startYear ,@Param("endYear") String endYear);
}
