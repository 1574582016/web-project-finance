package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnPaperMapper;
import com.sky.model.LearnPaper;
import com.sky.service.LearnPaperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Service
@Transactional
public class LearnPaperServiceImpl extends ServiceImpl<LearnPaperMapper, LearnPaper> implements LearnPaperService {
}
