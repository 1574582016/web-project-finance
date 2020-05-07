package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockSectorLevelMapper;
import com.sky.model.StockSectorLevel;
import com.sky.service.StockSectorLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/5/7.
 */
@Service
@Transactional
public class StockSectorLevelServiceImpl extends ServiceImpl<StockSectorLevelMapper,StockSectorLevel> implements StockSectorLevelService {
}
