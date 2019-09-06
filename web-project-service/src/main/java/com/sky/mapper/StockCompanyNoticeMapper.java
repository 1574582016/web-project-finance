package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockCompanyNotice;
import com.sky.vo.StockNoticeCompany_VO;
import com.sky.vo.StockNoticeDetail_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/4.
 */
public interface StockCompanyNoticeMapper extends BaseMapper<StockCompanyNotice> {

    List<StockNoticeCompany_VO> getStockNoticeCompanyList(Pagination page,
                                                          @Param("stockCode") String stockCode,
                                                          @Param("stockName") String stockName,
                                                          @Param("startDay") String startDay,
                                                          @Param("endDay") String endDay);

    List<StockNoticeDetail_VO> getStockNoticeDetailList(Pagination page,
                                                        @Param("stockCode") String stockCode,
                                                        @Param("startDay") String startDay,
                                                        @Param("endDay") String endDay);
}
