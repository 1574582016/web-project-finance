package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnDiarymMapper;
import com.sky.model.LearnDiary;
import com.sky.service.LearnDiaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/13.
 */
@Service
@Transactional
public class LearnDiaryServiceImpl extends ServiceImpl<LearnDiarymMapper, LearnDiary> implements LearnDiaryService {
    @Override
    public Page<LearnDiary> getLearnDiaryList(Integer page, Integer size, String name, String type, String startDate, String endDate) {
        Page<LearnDiary> pageInfo = new Page<LearnDiary>(page, size);
        List<LearnDiary> list = baseMapper.getLearnDiaryList( pageInfo,name, type, startDate, endDate);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
