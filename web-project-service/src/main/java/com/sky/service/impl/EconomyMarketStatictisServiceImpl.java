package com.sky.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.EconomyMarketStatictisMapper;
import com.sky.model.EconomyMarketStatictis;
import com.sky.service.EconomyMarketStatictisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/22.
 */
@Service
@Transactional
public class EconomyMarketStatictisServiceImpl extends ServiceImpl<EconomyMarketStatictisMapper,EconomyMarketStatictis> implements EconomyMarketStatictisService {
}
