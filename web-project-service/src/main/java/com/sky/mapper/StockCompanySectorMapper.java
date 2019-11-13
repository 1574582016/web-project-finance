package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanySector;
import com.sky.vo.CompanySectorVO;
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
}
