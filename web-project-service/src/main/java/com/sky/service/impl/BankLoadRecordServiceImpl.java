package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.BankLoadRecordMapper;
import com.sky.model.BankLoadRecord;
import com.sky.service.BankLoadRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankLoadRecordServiceImpl extends ServiceImpl<BankLoadRecordMapper, BankLoadRecord> implements BankLoadRecordService {

    @Override
    public Page<BankLoadRecord> getBankLoadRecordList(Integer page, Integer size, String bankName, String bankType, String startDay, String endDay) {
        Page<BankLoadRecord> pageInfo = new Page<BankLoadRecord>(page, size);
        List<BankLoadRecord> list = baseMapper.getBankLoadRecordList(pageInfo,bankName, bankType, startDay, endDay);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
