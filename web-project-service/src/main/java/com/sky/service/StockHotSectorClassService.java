package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockHotSectorClass;
import com.sky.vo.StockHotSectorClass_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2020/5/6.
 */
public interface StockHotSectorClassService extends IService<StockHotSectorClass> {

    List<StockHotSectorClass_VO> getStockHotSectorClassList(String firstSector,
                                                            String secondSector,
                                                            String thirdSector,
                                                            String forthSector,
                                                            String fiveSector);
}
