package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyNotice;
import com.sky.vo.StockNoticeCompany_VO;
import com.sky.vo.StockNoticeDetail_VO;

/**
 * Created by ThinkPad on 2019/9/4.
 */
public interface StockCompanyNoticeService extends IService<StockCompanyNotice> {

    boolean spiderStockCompanyNotice(Integer bigType ,Integer middleType ,Integer page);

    Page<StockNoticeCompany_VO> getStockNoticeCompanyList(Integer page,
                                                          Integer size,
                                                          String stockCode ,
                                                          String stockName ,
                                                          String startDay ,
                                                          String endDay );

    Page<StockNoticeDetail_VO> getStockNoticeDetailList(Integer page,
                                                         Integer size,
                                                         String stockCode ,
                                                         String startDay ,
                                                         String endDay );
}
