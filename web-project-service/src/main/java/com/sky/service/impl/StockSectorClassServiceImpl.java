package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockSectorClassMapper;
import com.sky.model.StockSectorClass;
import com.sky.service.StockSectorClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Service
@Transactional
public class StockSectorClassServiceImpl extends ServiceImpl<StockSectorClassMapper,StockSectorClass> implements StockSectorClassService {
}
