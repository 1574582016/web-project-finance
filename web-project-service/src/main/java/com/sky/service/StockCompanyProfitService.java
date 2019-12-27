package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyProfit;
import com.sky.vo.CompanyProfit_VO;
import com.sky.vo.StockCompanyProfitVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
public interface StockCompanyProfitService extends IService<StockCompanyProfit> {

    boolean spiderStockCompanyProfit(String stockCode ,Integer page);

    List<StockCompanyProfitVO> getCompanyProfitGrowList(String stockCode);

    List<CompanyProfit_VO> getRecentCompanyProfitList( String stockCode , String startYear , String endYear);

    CompanyProfit_VO getMaxAndMinCompanyProfit( String stockCode , String startYear , String endYear);

    BigDecimal calculateCompanyProfitIncreaseRate(String stockCode , String startYear , String endYear);
}
