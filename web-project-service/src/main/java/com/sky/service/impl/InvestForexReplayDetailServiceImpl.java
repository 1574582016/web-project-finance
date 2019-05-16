package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.InvestForexReplayDetailMapper;
import com.sky.model.InvestForexReplayDetail;
import com.sky.service.InvestForexReplayDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Service
@Transactional
public class InvestForexReplayDetailServiceImpl extends ServiceImpl<InvestForexReplayDetailMapper,InvestForexReplayDetail> implements InvestForexReplayDetailService {
}
