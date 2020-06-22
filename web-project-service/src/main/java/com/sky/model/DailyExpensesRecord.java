package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/6/22.
 */
@Data
@TableName("daily_expenses_record")
public class DailyExpensesRecord extends BaseModel<DailyExpensesRecord> {

    /**
     *一级类型
     */
    @TableField("first_type")
    private Integer firstType ;

    /**
     *二级分类
     */
    @TableField("second_type")
    private Integer secondType ;

    /**
     *花销日期
     */
    @TableField("expenses_day")
    private String expensesDay ;

    /**
     *花销金额
     */
    @TableField("expenses_money")
    private String expensesMoney ;
}
