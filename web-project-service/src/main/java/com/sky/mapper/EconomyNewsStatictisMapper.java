package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.EconomyNewsStatictis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/14.
 */
public interface EconomyNewsStatictisMapper extends BaseMapper<EconomyNewsStatictis> {

    List<EconomyNewsStatictis> getEconomyNewsStatisticsList(Pagination page);
}
