package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.EconomyNewsInfluenceMapper;
import com.sky.model.EconomyNewsInfluence;
import com.sky.service.EconomyNewsInfluenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Service
@Transactional
public class EconomyNewsInfluenceServiceImpl extends ServiceImpl<EconomyNewsInfluenceMapper,EconomyNewsInfluence> implements EconomyNewsInfluenceService {
}
