package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.InvestForexOperate;

/**
 * Created by ThinkPad on 2019/5/20.
 */
public interface InvestForexOperateService extends IService<InvestForexOperate> {

    Page<InvestForexOperate> getInvestForexOperateList(Integer page, Integer size , String investStrategy , String startDate , String endDate  , String userId );
}
