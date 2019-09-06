package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyNoticeClassMapper;
import com.sky.model.StockCompanyNoticeClass;
import com.sky.service.StockCompanyNoticeClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/9/5.
 */
@Service
@Transactional
public class StockCompanyNoticeClassServiceImpl extends ServiceImpl<StockCompanyNoticeClassMapper,StockCompanyNoticeClass> implements StockCompanyNoticeClassService {

    @Override
    public String getStockNoticeClassCode(String bigClass, String middleClass, String nticeType) {
        return baseMapper.getStockNoticeClassCode(bigClass ,middleClass ,nticeType);
    }
}
