package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.ContryMacroEconomyIndex;
import com.sky.vo.MacroEconomy_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/26.
 */
public interface ContryMacroEconomyIndexService extends IService<ContryMacroEconomyIndex> {

    List<MacroEconomy_VO> getContryMacroEconomy(String contry , String indexCode ,String startDay ,String endDay);
}
