package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.DailyExpensesRecord;
import com.sky.vo.ExpensesRecord_VO;

/**
 * Created by ThinkPad on 2020/6/22.
 */
public interface DailyExpensesRecordService extends IService<DailyExpensesRecord> {

    Page<ExpensesRecord_VO> getDailyExpensesRecordList(Integer page , Integer size , String  firstType, String secondType , String  startDay, String endDay );
}
