package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanySector;
import com.sky.vo.CompanySectorVO;
import com.sky.vo.CreateCompanyWorld_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/14.
 */
public interface StockCompanySectorService extends IService<StockCompanySector> {

    List<CompanySectorVO> getStockCompanySectorList(String stockCode ,
                                                    String stockName ,
                                                    String firstSector ,
                                                    String secondSector ,
                                                    String thirdSecotor ,
                                                    String forthSector  ,
                                                    String firstHot ,
                                                    String secondHot ,
                                                    String thirdHot ,
                                                    String forthHot );

    List<CreateCompanyWorld_VO> getCreateCompanyWorldList(String stockCode ,
                                                          String firstSector ,
                                                          String secondSector ,
                                                          String thirdSecotor ,
                                                          String forthSector);
}
