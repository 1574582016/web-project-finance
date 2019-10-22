package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockIndexConstituent;
import com.sky.vo.IndexConstituent_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/2.
 */
public interface StockIndexConstituentService extends IService<StockIndexConstituent>{

    List<IndexConstituent_VO> getStockIndexConstituentList(String indexName);
}
