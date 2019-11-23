package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.SectorHandleDealDataMapper;
import com.sky.model.SectorHandleDealData;
import com.sky.service.SectorHandleDealDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class SectorHandleDealDataServiceImpl extends ServiceImpl<SectorHandleDealDataMapper,SectorHandleDealData> implements SectorHandleDealDataService {
}
