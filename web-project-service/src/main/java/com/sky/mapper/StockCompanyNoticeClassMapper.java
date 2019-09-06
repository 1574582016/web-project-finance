package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCompanyNoticeClass;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ThinkPad on 2019/9/5.
 */
public interface StockCompanyNoticeClassMapper extends BaseMapper<StockCompanyNoticeClass> {

    String getStockNoticeClassCode(@Param("bigClass") String bigClass ,@Param("middleClass") String middleClass ,@Param("nticeType") String nticeType );
}
