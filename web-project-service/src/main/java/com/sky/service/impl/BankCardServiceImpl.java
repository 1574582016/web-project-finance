package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.BankCardMapper;
import com.sky.model.BankCard;
import com.sky.service.BankCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {

    @Override
    public Page<BankCard> getBankCardList(Integer page, Integer size, String bankName, String bankType) {
        Page<BankCard> pageInfo = new Page<BankCard>(page, size);
        List<BankCard> list = baseMapper.getBankCardList(pageInfo,bankName, bankType);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
