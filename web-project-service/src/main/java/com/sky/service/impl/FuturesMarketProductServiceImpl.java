package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.FuturesMarketProductMapper;
import com.sky.model.FuturesMarketProduct;
import com.sky.service.FuturesMarketProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/6/25.
 */
@Service
@Transactional
public class FuturesMarketProductServiceImpl extends ServiceImpl<FuturesMarketProductMapper,FuturesMarketProduct> implements FuturesMarketProductService {

}
