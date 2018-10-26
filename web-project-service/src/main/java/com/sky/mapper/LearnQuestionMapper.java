package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.LearnQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/18.
 */
public interface LearnQuestionMapper extends BaseMapper<LearnQuestion> {

    List<LearnQuestion> getLearnQuestionList(Pagination page, @Param("content") String content, @Param("type") String type, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
