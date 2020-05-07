package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockHotSectorClass;
import com.sky.vo.StockHotSectorClass_VO;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Created by ThinkPad on 2020/5/6.
 */
public interface StockHotSectorClassMapper extends BaseMapper<StockHotSectorClass> {

    List<StockHotSectorClass_VO> getStockHotSectorClassList(@Param("firstSector") String firstSector,
                                                            @Param("secondSector") String secondSector,
                                                            @Param("thirdSector") String thirdSector,
                                                            @Param("forthSector") String forthSector,
                                                            @Param("fiveSector") String fiveSector ,
                                                            @Param("sectorTypes") String sectorTypes);
}
