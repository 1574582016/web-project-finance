package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.InvestForexReplay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/16.
 */
public interface InvestForexReplayMapper extends BaseMapper<InvestForexReplay> {

    List<InvestForexReplay> getInvestForexReplayList(Pagination page, @Param("investStrategy") String investStrategy, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("userId") String userId);
}
