package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("bank_load_record")
public class BankLoadRecord extends BaseModel<BankLoadRecord> {

    /**
     *银行ID
     */
    @TableField("bank_id")
    private Integer BankId ;

    /**
     *套现日期
     */
    @TableField("cash_out_day")
    private String CashOutDay ;

    /**
     *套现金额
     */
    @TableField("cash_out_money")
    private BigDecimal CashOutMoney ;

    /**
     *套现到账金额
     */
    @TableField("cash_out_receive")
    private BigDecimal CashOutReceive ;

    /**
     *出账日期
     */
    @TableField("out_bill_day")
    private String OutBillDay ;

    /**
     *出账金额
     */
    @TableField("out_bill_money")
    private BigDecimal OutBillMoney ;

    /**
     *还款日期
     */
    @TableField("pay_back_day")
    private String PayBackDay ;

    /**
     *还款金额
     */
    @TableField("pay_back_money")
    private BigDecimal PayBackMoney ;

    /**
     *银行卡名称
     */
    @TableField(exist = false)
    private String bankName ;

    /**
     *银行卡类型1-商业银行，2-多元金融
     */
    @TableField(exist = false)
    private Integer bankType ;
}
