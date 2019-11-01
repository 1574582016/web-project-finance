package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ContryMacroEconomyClassMapper;
import com.sky.model.ContryMacroEconomyClass;
import com.sky.service.ContryMacroEconomyClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/11/1.
 */
@Service
@Transactional
public class ContryMacroEconomyClassServiceImpl extends ServiceImpl<ContryMacroEconomyClassMapper,ContryMacroEconomyClass> implements ContryMacroEconomyClassService {
}
