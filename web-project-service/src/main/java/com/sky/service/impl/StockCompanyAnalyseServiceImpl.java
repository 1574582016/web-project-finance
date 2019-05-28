package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyAnalyseMapper;
import com.sky.model.StockCompanyAnalyse;
import com.sky.service.StockCompanyAnalyseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCompanyAnalyseServiceImpl extends ServiceImpl<StockCompanyAnalyseMapper,StockCompanyAnalyse> implements StockCompanyAnalyseService {
}
