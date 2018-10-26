package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.LearnDiary;


/**
 * Created by ThinkPad on 2018/10/13.
 */
public interface LearnDiaryService extends IService<LearnDiary> {

    Page<LearnDiary> getLearnDiaryList(Integer page, Integer size, String name, String type, String startDate, String endDate);
}

