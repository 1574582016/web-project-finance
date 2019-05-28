package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyProductMapper;
import com.sky.model.StockCompanyProduct;
import com.sky.service.StockCompanyProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCompanyProductServiceImpl extends ServiceImpl<StockCompanyProductMapper,StockCompanyProduct> implements StockCompanyProductService {
}
