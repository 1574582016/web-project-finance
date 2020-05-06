package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/5/6.
 */
@Data
@TableName("stock_hot_sector_class")
public class StockHotSectorClass extends BaseModel<StockHotSectorClass> {

    /**
     *一级行业
     */
    @TableField("first_sector")
    private String firstSector ;

    /**
     *二级行业
     */
    @TableField("second_sector")
    private String secondSector ;

    /**
     *三级行业
     */
    @TableField("third_sector")
    private String thirdSector ;

    /**
     *四级行业
     */
    @TableField("forth_sector")
    private String forthSector ;


    @TableField("hot_code")
    private String hotCode ;

    @TableField("hot_name")
    private String hotName ;
}
