package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.DailyExpensesClassMapper;
import com.sky.model.DailyExpensesClass;
import com.sky.service.DailyExpensesClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/6/22.
 */
@Service
@Transactional
public class DailyExpensesClassServiceImpl extends ServiceImpl<DailyExpensesClassMapper, DailyExpensesClass> implements DailyExpensesClassService {
}
