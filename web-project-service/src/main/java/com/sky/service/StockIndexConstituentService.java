package com.sky.service;

import com.sky.vo.IndexConstituent_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/2.
 */
public interface StockIndexConstituentService {

    List<IndexConstituent_VO> getStockIndexConstituentList(String indexName);
}
