package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.StockCompanySector;
import com.sky.vo.CompanySectorVO;
import com.sky.vo.CreateCompanyWorld_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/14.
 */
public interface StockCompanySectorMapper extends BaseMapper<StockCompanySector> {

    List<CompanySectorVO> getStockCompanySectorList(@Param("stockCode") String stockCode ,
                                                    @Param("stockName") String stockName ,
                                                    @Param("firstSector") String firstSector ,
                                                    @Param("secondSector") String secondSector ,
                                                    @Param("thirdSecotor") String thirdSecotor ,
                                                    @Param("forthSector") String forthSector  ,
                                                    @Param("firstHot") String firstHot ,
                                                    @Param("secondHot") String secondHot ,
                                                    @Param("thirdHot") String thirdHot ,
                                                    @Param("forthHot") String forthHot );


    List<CreateCompanyWorld_VO> getCreateCompanyWorldList(@Param("stockCode") String stockCode ,
                                                          @Param("firstSector") String firstSector ,
                                                          @Param("secondSector") String secondSector ,
                                                          @Param("thirdSecotor") String thirdSecotor ,
                                                          @Param("forthSector") String forthSector );

    List<CreateCompanyWorld_VO> getStockCompanyPoolList(Pagination page ,
                                                        @Param("stockCode") String stockCode ,
                                                        @Param("firstSector") String firstSector ,
                                                        @Param("secondSector") String secondSector ,
                                                        @Param("thirdSecotor") String thirdSecotor ,
                                                        @Param("forthSector") String forthSector ,
                                                        @Param("companyLevel") String companyLevel);
}
