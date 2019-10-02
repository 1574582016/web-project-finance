package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockIndexConstituent;
import com.sky.vo.IndexConstituent_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/2.
 */
public interface StockIndexConstituentMapper extends BaseMapper<StockIndexConstituent> {

    List<IndexConstituent_VO> getStockIndexConstituentList(@Param("indexName") String indexName);
}
