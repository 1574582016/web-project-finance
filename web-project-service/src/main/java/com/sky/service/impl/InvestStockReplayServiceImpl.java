package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.InvestStockReplayMapper;
import com.sky.model.InvestStockReplay;
import com.sky.service.InvestStockReplayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Service
@Transactional
public class InvestStockReplayServiceImpl extends ServiceImpl<InvestStockReplayMapper,InvestStockReplay> implements InvestStockReplayService {
}
