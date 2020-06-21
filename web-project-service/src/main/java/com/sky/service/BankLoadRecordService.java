package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.BankCard;
import com.sky.model.BankLoadRecord;

public interface BankLoadRecordService extends IService<BankLoadRecord> {

    Page<BankLoadRecord> getBankLoadRecordList(Integer page , Integer size , String  bankName, String bankType , String  startDay, String endDay );
}
