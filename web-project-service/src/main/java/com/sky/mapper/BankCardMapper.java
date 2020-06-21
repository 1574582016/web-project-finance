package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.BankCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankCardMapper extends BaseMapper<BankCard> {

    List<BankCard> getBankCardList(Pagination page , @Param("bankName") String  bankName, @Param("bankType") String bankType);
}
