package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ContryMacroEconomyIndexMapper;
import com.sky.model.ContryMacroEconomyIndex;
import com.sky.service.ContryMacroEconomyIndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/26.
 */
@Service
@Transactional
public class ContryMacroEconomyIndexServiceImpl extends ServiceImpl<ContryMacroEconomyIndexMapper,ContryMacroEconomyIndex> implements ContryMacroEconomyIndexService {
}
