package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/12/24.
 */
@Data
@TableName("stock_investor_info")
public class StockInvestorInfo extends BaseModel<StockInvestorInfo> {

    /**
     *投资人编码
     */
    @TableField("investor_code")
    private String investorCode ;

    /**
     *投资人名称
     */
    @TableField("investor_name")
    private String investorName ;

    /**
     *投资人类型
     */
    @TableField("type_name")
    private String typeName ;

    /**
     *类型编码
     */
    @TableField("type_code")
    private String typeCode ;

}
