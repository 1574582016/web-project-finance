package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.InvestStockReplayDetailMapper;
import com.sky.model.InvestStockReplayDetail;
import com.sky.service.InvestStockReplayDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Service
@Transactional
public class InvestStockReplayDetailServiceImpl extends ServiceImpl<InvestStockReplayDetailMapper,InvestStockReplayDetail> implements InvestStockReplayDetailService {
}
