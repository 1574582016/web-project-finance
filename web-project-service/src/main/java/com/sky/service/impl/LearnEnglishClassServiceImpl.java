package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnEnglishClassMapper;
import com.sky.model.LearnEnglishClass;
import com.sky.service.LearnEnglishClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/2/14.
 */
@Service
@Transactional
public class LearnEnglishClassServiceImpl extends ServiceImpl<LearnEnglishClassMapper,LearnEnglishClass> implements LearnEnglishClassService {
}
