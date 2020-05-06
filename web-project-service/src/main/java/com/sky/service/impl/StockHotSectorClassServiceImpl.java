package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockHotSectorClassMapper;
import com.sky.model.StockHotSectorClass;
import com.sky.service.StockHotSectorClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/5/6.
 */
@Service
@Transactional
public class StockHotSectorClassServiceImpl extends ServiceImpl<StockHotSectorClassMapper,StockHotSectorClass> implements StockHotSectorClassService {
}
