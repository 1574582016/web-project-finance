package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.InvestForexOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/20.
 */
public interface InvestForexOperateMapper extends BaseMapper<InvestForexOperate> {

    List<InvestForexOperate> getInvestForexOperateList(Pagination page, @Param("investStrategy") String investStrategy, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("userId") String userId);
}
