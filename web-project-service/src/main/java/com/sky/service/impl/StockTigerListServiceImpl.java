package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockTigerListMapper;
import com.sky.model.StockTigerList;
import com.sky.service.StockTigerListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@Service
@Transactional
public class StockTigerListServiceImpl extends ServiceImpl<StockTigerListMapper,StockTigerList> implements StockTigerListService {
}
