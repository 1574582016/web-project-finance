package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.EnglishRootAffixMapper;
import com.sky.model.EnglishRootAffix;
import com.sky.service.EnglishRootAffixService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/12/15/015.
 */
@Service
@Transactional
public class EnglishRootAffixServiceImpl extends ServiceImpl<EnglishRootAffixMapper,EnglishRootAffix> implements EnglishRootAffixService {
}
