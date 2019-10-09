package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockIndexClassMapper;
import com.sky.model.StockIndexClass;
import com.sky.service.StockIndexClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/9.
 */
@Service
@Transactional
public class StockIndexClassServiceImpl extends ServiceImpl<StockIndexClassMapper,StockIndexClass> implements StockIndexClassService {
}
