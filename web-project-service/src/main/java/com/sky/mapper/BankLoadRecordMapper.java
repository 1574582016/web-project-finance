package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.BankLoadRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankLoadRecordMapper extends BaseMapper<BankLoadRecord> {

    List<BankLoadRecord> getBankLoadRecordList(Pagination page , @Param("bankName") String  bankName, @Param("bankType") String bankType,@Param("startDay") String startDay,@Param("endDay") String endDay);
}
