package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.DailyExpensesRecord;
import com.sky.vo.ExpensesRecord_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2020/6/22.
 */
public interface DailyExpensesRecordMapper extends BaseMapper<DailyExpensesRecord> {

    List<ExpensesRecord_VO> getDailyExpensesRecordList(Pagination page , @Param("firstType") String  firstType,@Param("secondType") String secondType ,@Param("startDay") String  startDay,@Param("endDay") String endDay );
}
