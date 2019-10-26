package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/25.
 */
@Data
@TableName("stock_company_cash_flow")
public class StockCompanyCashFlow extends BaseModel<StockCompanyCashFlow> {

    /**
     *编码
     */
    @TableField(value = "stock_code")
    private String stockCode ;

    /**
     *公布日期
     */
    @TableField(value = "publish_day")
    private String publishDay ;

    /**
     *经营活动总现金流
     */
    @TableField(value = "business_total_cash_flow")
    private String businessTotalCashFlow ;

    /**
     *经营活动总流入现金流
     */
    @TableField(value = "business_total_in_cash_flow")
    private String businessTotalInCashFlow ;

    /**
     *销售商品、提供劳务收到的现金——主营收入
     */
    @TableField(value = "main_business_income")
    private String mainBusinessIncome ;

    /**
     *客户存款和同业存放款项净增加额——客户还钱
     */
    @TableField(value = "customer_repay_income")
    private String customerRepayIncome ;

    /**
     *向中央银行借款净增加额——银行贷款
     */
    @TableField(value = "bank_loan_income")
    private String bankLoanIncome ;

    /**
     *收取利息、手续费及佣金的现金——业务费
     */
    @TableField(value = "business_commission_income")
    private String businessCommissionIncome ;

    /**
     *发放贷款及垫款的净减少额——外借还款
     */
    @TableField(value = "loan_repay_income")
    private String loanRepayIncome ;

    /**
     *收到的税费返还——税收政策优惠
     */
    @TableField(value = "tax_repay_income")
    private String taxRepayIncome ;

    /**
     *收到其他与经营活动有关的现金
     */
    @TableField(value = "business_other_income")
    private String businessOtherIncome ;

    /**
     *经营活动总流出现金流
     */
    @TableField(value = "business_total_out_cash_flow")
    private String businessTotalOutCashFlow ;

    /**
     *购买商品、接受劳务支付的现金——主营业务支出
     */
    @TableField(value = "main_business_pay")
    private String mainBusinessPay ;

    /**
     *客户贷款及垫款净增加额——客户分期购买
     */
    @TableField(value = "customer_installment_buy")
    private String customerInstallmentBuy ;

    /**
     *存放中央银行和同业款项净增加额——付给银行贷款
     */
    @TableField(value = "bank_loan_pay")
    private String bankLoanPay ;

    /**
     *支付利息、手续费及佣金的现金——办理业务费用
     */
    @TableField(value = "business_commission_pay")
    private String businessCommissionPay ;

    /**
     *支付给职工以及为职工支付的现金——工资
     */
    @TableField(value = "staff_salary_pay")
    private String staffSalaryPay ;

    /**
     *支付的各项税费——缴纳的税收
     */
    @TableField(value = "tax_pay")
    private String taxPay ;

    /**
     *支付其他与经营活动有关的现金
     */
    @TableField(value = "business_other_pay")
    private String businessOtherPay ;

    /**
     *投资活动总现金流
     */
    @TableField(value = "invest_total_cash_flow")
    private String investTotalCashFlow ;

    /**
     *投资活动总流入现金流
     */
    @TableField(value = "invest_total_in_cash_flow")
    private String investTotalInCashFlow ;

    /**
     *收回投资收到的现金——投资成本
     */
    @TableField(value = "invest_cost_income")
    private String investCostIncome ;

    /**
     *取得投资收益收到的现金——投资收益
     */
    @TableField(value = "invest_profit_income")
    private String investProfitIncome ;

    /**
     *处置固定资产、无形资产和其他长期资产收回的现金净额——资产售卖
     */
    @TableField(value = "asset_sell_income")
    private String assetSellIncome ;

    /**
     *处置子公司及其他营业单位收到的现金净额——子公司售卖
     */
    @TableField(value = "son_company_sell_income")
    private String sonCompanySellIncome ;

    /**
     *投资活动总流出现金流
     */
    @TableField(value = "invest_total_out_cash_flow")
    private String investTotalOutCashFlow ;

    /**
     *投资支付的现金——投资支付
     */
    @TableField(value = "invest_cost_pay")
    private String investCostPay ;

    /**
     *购建固定资产、无形资产和其他长期资产支付的现金——购买资产
     */
    @TableField(value = "asset_buy_pay")
    private String assetBuyPay ;

    /**
     *取得子公司及其他营业单位支付的现金净额——投资给子公司的钱
     */
    @TableField(value = "son_company_pay")
    private String sonCompanyPay ;

    /**
     *投资活动总现金流
     */
    @TableField(value = "collect_total_cash_flow")
    private String collectTotalCashFlow ;

    /**
     *投资活动总流入现金流
     */
    @TableField(value = "collect_total_in_cash_flow")
    private String collectTotalInCashFlow ;

    /**
     *吸收投资收到的现金——投资人出资
     */
    @TableField(value = "investor_pay_income")
    private String investorPayIncome ;

    /**
     *子公司吸收少数股东投资收到的现金——子公司出资人出资
     */
    @TableField(value = "son_company_pay_income")
    private String sonCompanyPayIncome ;

    /**
     *取得借款收到的现金——借的款项
     */
    @TableField(value = "loan_pay_income")
    private String loanPayIncome ;

    /**
     *投资活动总流出现金流
     */
    @TableField(value = "collect_total_out_cash_flow")
    private String collectTotalOutCashFlow ;

    /**
     *偿还债务支付的现金——还债
     */
    @TableField(value = "pay_loan")
    private String payLoan ;

    /**
     *分配股利、利润或偿付利息支付的现金——发放投资收益
     */
    @TableField(value = "investor_profit")
    private String investorProfit ;

    /**
     *子公司支付给少数股东的股利、利润——少数投资者投资收益
     */
    @TableField(value = "minority_investor_profit")
    private String minorityInvestorProfit ;

    /**
     *支付其他与筹资活动有关的现金
     */
    @TableField(value = "pay_son_company_profit")
    private String paySonCompanyProfit ;



}
