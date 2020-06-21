package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.BankCard;

public interface BankCardService extends IService<BankCard> {

    Page<BankCard> getBankCardList(Integer page , Integer size , String  bankName, String bankType);
}
