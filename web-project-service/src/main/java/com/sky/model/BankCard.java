package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("bank_card")
public class BankCard extends BaseModel<BankCard> {

    /**
     *银行卡名称
     */
    @TableField("bank_name")
    private String bankName ;

    /**
     *银行卡类型1-商业银行，2-多元金融
     */
    @TableField("bank_type")
    private Integer bankType ;

    /**
     *授信额度
     */
    @TableField("credit_money")
    private BigDecimal creditMoney ;

    /**
     *年费
     */
    @TableField("year_money")
    private BigDecimal yearMoney ;

    /**
     *开始时间
     */
    @TableField("start_day")
    private String startDay ;

    /**
     *结束时间
     */
    @TableField("end_day")
    private String endDay ;
}
