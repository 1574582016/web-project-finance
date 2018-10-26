package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnQuestionMapper;
import com.sky.model.LearnQuestion;
import com.sky.service.LearnQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Service
@Transactional
public class LearnQuestionServiceImpl extends ServiceImpl<LearnQuestionMapper ,LearnQuestion> implements LearnQuestionService {
    @Override
    public Page<LearnQuestion> getLearnQuestionList(Integer page, Integer size, String content, String type, String startDate, String endDate) {
        Page<LearnQuestion> pageInfo = new Page<LearnQuestion>(page, size);
        List<LearnQuestion> list = baseMapper.getLearnQuestionList( pageInfo,content, type, startDate, endDate);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
