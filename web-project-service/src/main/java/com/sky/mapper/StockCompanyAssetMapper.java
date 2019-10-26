package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyAsset;
import com.sky.vo.StockCompanyAssetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
public interface StockCompanyAssetMapper extends BaseMapper<StockCompanyAsset> {

    List<StockCompanyAssetVO> getCompanyAssetGrowList(@Param("stockCode") String stockCode);
}
