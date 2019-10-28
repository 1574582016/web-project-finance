package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ContryMacroEconomyIndexMapper;
import com.sky.model.ContryMacroEconomyIndex;
import com.sky.service.ContryMacroEconomyIndexService;
import com.sky.vo.MacroEconomy_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/26.
 */
@Service
@Transactional
public class ContryMacroEconomyIndexServiceImpl extends ServiceImpl<ContryMacroEconomyIndexMapper,ContryMacroEconomyIndex> implements ContryMacroEconomyIndexService {

    @Override
    public List<MacroEconomy_VO> getContryMacroEconomy(String contry, String indexCode ,String startDay ,String endDay) {
        return baseMapper.getContryMacroEconomy( contry, indexCode , startDay , endDay);
    }
}
