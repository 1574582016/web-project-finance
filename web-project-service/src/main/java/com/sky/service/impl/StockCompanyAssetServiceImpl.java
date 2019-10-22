package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyAssetMapper;
import com.sky.model.StockCompanyAsset;
import com.sky.service.StockCompanyAssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Service
@Transactional
public class StockCompanyAssetServiceImpl extends ServiceImpl<StockCompanyAssetMapper,StockCompanyAsset> implements StockCompanyAssetService {
}
