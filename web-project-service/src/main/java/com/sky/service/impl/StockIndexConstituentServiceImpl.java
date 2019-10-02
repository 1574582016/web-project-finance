package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockIndexConstituentMapper;
import com.sky.model.StockIndexConstituent;
import com.sky.service.StockIndexConstituentService;
import com.sky.vo.IndexConstituent_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/2.
 */
@Service
@Transactional
public class StockIndexConstituentServiceImpl extends ServiceImpl<StockIndexConstituentMapper,StockIndexConstituent> implements StockIndexConstituentService {
    @Override
    public List<IndexConstituent_VO> getStockIndexConstituentList(String indexName) {
        return baseMapper.getStockIndexConstituentList(indexName);
    }
}
