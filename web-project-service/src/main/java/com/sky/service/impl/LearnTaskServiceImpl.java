package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnTaskMapper;
import com.sky.model.LearnTask;
import com.sky.service.LearnTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2018/10/16.
 */
@Service
@Transactional
public class LearnTaskServiceImpl extends ServiceImpl<LearnTaskMapper,LearnTask> implements LearnTaskService {
}
