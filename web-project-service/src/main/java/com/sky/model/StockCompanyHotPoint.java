package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/9/2.
 */
@Data
@TableName("stock_company_hot_point")
public class StockCompanyHotPoint extends BaseModel<StockCompanyHotPoint> {

    /**
     *编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *名称
     */
    @TableField("point_name")
    private String pointName ;

}
