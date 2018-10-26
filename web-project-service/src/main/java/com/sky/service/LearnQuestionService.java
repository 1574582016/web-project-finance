package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.LearnQuestion;

/**
 * Created by ThinkPad on 2018/10/18.
 */
public interface LearnQuestionService extends IService<LearnQuestion> {

    Page<LearnQuestion> getLearnQuestionList(Integer page, Integer size, String content, String type, String startDate, String endDate);
}
