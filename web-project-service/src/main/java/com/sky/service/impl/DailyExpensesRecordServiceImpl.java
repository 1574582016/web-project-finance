package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.DailyExpensesRecordMapper;
import com.sky.model.DailyExpensesRecord;
import com.sky.service.DailyExpensesRecordService;
import com.sky.vo.ExpensesRecord_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2020/6/22.
 */
@Service
@Transactional
public class DailyExpensesRecordServiceImpl extends ServiceImpl<DailyExpensesRecordMapper, DailyExpensesRecord> implements DailyExpensesRecordService {

    @Override
    public Page<ExpensesRecord_VO> getDailyExpensesRecordList(Integer page, Integer size, String firstType, String secondType, String startDay, String endDay) {
        Page<ExpensesRecord_VO> pageInfo = new Page<ExpensesRecord_VO>(page, size);
        List<ExpensesRecord_VO> list = baseMapper.getDailyExpensesRecordList(pageInfo,firstType, secondType, startDay, endDay);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
