package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/6/22.
 */
@Data
@TableName("daily_expenses_class")
public class DailyExpensesClass extends BaseModel<DailyExpensesClass> {

    /**
     *父类id
     */
    @TableField("parent_id")
    private Integer parentId ;

    /**
     *类型名称
     */
    @TableField("class_name")
    private String className ;

    /**
     *类型排序
     */
    @TableField("class_order")
    private Integer classOrder ;
}
