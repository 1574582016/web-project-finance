package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockHotSectorClassMapper;
import com.sky.model.StockHotSectorClass;
import com.sky.service.StockHotSectorClassService;
import com.sky.vo.StockHotSectorClass_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2020/5/6.
 */
@Service
@Transactional
public class StockHotSectorClassServiceImpl extends ServiceImpl<StockHotSectorClassMapper,StockHotSectorClass> implements StockHotSectorClassService {

    @Override
    public List<StockHotSectorClass_VO> getStockHotSectorClassList(String firstSector, String secondSector, String thirdSector, String forthSector, String fiveSector) {
        return baseMapper.getStockHotSectorClassList(firstSector , secondSector , thirdSector , forthSector , fiveSector);
    }
}
