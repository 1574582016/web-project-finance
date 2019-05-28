package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCompanyProductMapper extends BaseMapper<StockCompanyProduct> {

    List<StockCompanyProduct> getStockCompanyConstruct(@Param("stockCode") String stockCode);
}
