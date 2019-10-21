package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.FuturesClassMapper;
import com.sky.model.FuturesClass;
import com.sky.service.FuturesClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/21.
 */
@Service
@Transactional
public class FuturesClassServiceImpl extends ServiceImpl<FuturesClassMapper,FuturesClass> implements FuturesClassService {
}
