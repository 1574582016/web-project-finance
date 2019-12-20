package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.LearnEnglishWordMapper;
import com.sky.model.LearnEnglishWord;
import com.sky.service.LearnEnglishWordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/11.
 */
@Service
@Transactional
public class LearnEnglishWordServiceImpl extends ServiceImpl<LearnEnglishWordMapper,LearnEnglishWord> implements LearnEnglishWordService {

    @Override
    public List<LearnEnglishWord> getCommonRootEnglishList(String root) {
        return baseMapper.getCommonRootEnglishList(root);
    }
}
