package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.InvestForexReplay;

/**
 * Created by ThinkPad on 2019/5/16.
 */
public interface InvestForexReplayService extends IService<InvestForexReplay> {

    Page<InvestForexReplay> getInvestForexReplayList(Integer page, Integer size ,String investStrategy , String startDate , String endDate  , String userId );
}
