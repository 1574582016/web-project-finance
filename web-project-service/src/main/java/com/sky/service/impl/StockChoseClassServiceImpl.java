package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockChoseClassMapper;
import com.sky.model.StockChoseClass;
import com.sky.service.StockChoseClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/9/7.
 */
@Service
@Transactional
public class StockChoseClassServiceImpl extends ServiceImpl<StockChoseClassMapper,StockChoseClass> implements StockChoseClassService {
}
