package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StatisticsDayNewsMapper;
import com.sky.model.StatisticsDayNews;
import com.sky.service.StatisticsDayNewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/4/29.
 */
@Service
@Transactional
public class StatisticsDayNewsServiceImpl extends ServiceImpl<StatisticsDayNewsMapper,StatisticsDayNews> implements StatisticsDayNewsService {
}
