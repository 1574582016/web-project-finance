package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.LearnDiary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/13.
 */
public interface LearnDiarymMapper extends BaseMapper<LearnDiary> {

    List<LearnDiary> getLearnDiaryList(Pagination page, @Param("name") String name, @Param("type") String type, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
