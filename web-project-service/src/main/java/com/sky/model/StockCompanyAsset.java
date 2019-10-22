package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Data
@TableName("stock_company_asset")
public class StockCompanyAsset extends BaseModel<StockCompanyAsset> {

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
     *总资产负债
     */
    @TableField(value = "total_asset_debt")
    private String totalAssetDebt ;

    /**
     *总资产
     */
    @TableField(value = "total_asset")
    private String totalAsset ;

    /**
     *总流动资产
     */
    @TableField(value = "total_flow_asset")
    private String totalFlowAsset ;

    /**
     *货币资产
     */
    @TableField(value = "flow_currency_asset")
    private String flowCurrencyAsset ;

    /**
     *预付款资产
     */
    @TableField(value = "flow_pay_asset")
    private String flowPayAsset ;

    /**
     *库存资产
     */
    @TableField(value = "flow_storage_asset")
    private String flowStorageAsset ;

    /**
     *应收账款资产
     */
    @TableField(value = "flow_bill_asset")
    private String flowBillAsset ;

    /**
     *其他应收账款资产
     */
    @TableField(value = "flow_other_bill")
    private String flowOtherBill ;

    /**
     *其他资产
     */
    @TableField(value = "flow_other_asset")
    private String flowOtherAsset ;

    /**
     *非流动总资产
     */
    @TableField(value = "total_unflow_asset")
    private String totalUnflowAsset ;

    /**
     *固定资产
     */
    @TableField(value = "unflow_fixed_asset")
    private String unflowFixedAsset ;

    /**
     *在建工程
     */
    @TableField(value = "unflow_build_project")
    private String unflowBuildProject ;

    /**
     *投资型房地产
     */
    @TableField(value = "unflow_invest_house")
    private String unflowInvestHouse ;

    /**
     *长期股权投资
     */
    @TableField(value = "unflow_stock_right")
    private String unflowStockRight ;

    /**
     *长期应收款
     */
    @TableField(value = "unflow_long_bill")
    private String unflowLongBill ;

    /**
     *可供出售金融资产
     */
    @TableField(value = "unflow_sell_finacial")
    private String unflowSellFinacial ;

    /**
     *无形资产
     */
    @TableField(value = "unflow_intangible_asset")
    private String unflowIntangibleAsset ;

    /**
     *商誉
     */
    @TableField(value = "unflow_company_reputation")
    private String unflowCompanyReputation ;

    /**
     *长期待摊费用
     */
    @TableField(value = "unflow_prepaid_expenses")
    private String unflowPrepaidExpenses ;

    /**
     *递延所得税资产
     */
    @TableField(value = "unflow_deferred_tax_asset")
    private String unflowDeferredTaxAsset ;

    /**
     *其他非流动资产
     */
    @TableField(value = "unflow_other_asset")
    private String unflowOtherAsset ;

    /**
     *总负债
     */
    @TableField(value = "total_debt")
    private String totalDebt ;

    /**
     *总流动负债
     */
    @TableField(value = "total_flow_debt")
    private String totalFlowDebt ;

    /**
     *短期借款
     */
    @TableField(value = "flow_sort_loan")
    private String flowSortLoan ;

    /**
     *向中央银行借款
     */
    @TableField(value = "flow_center_bank_loan")
    private String flowCenterBankLoan ;

    /**
     *吸收存款及同业存放(同行借贷)
     */
    @TableField(value = "flow_same_company_loan")
    private String flowSameCompanyLoan ;

    /**
     *应付票据及应付账款
     */
    @TableField(value = "flow_pay_bill")
    private String flowPayBill ;

    /**
     *预收款项
     */
    @TableField(value = "flow_advance_receive")
    private String flowAdvanceReceive ;

    /**
     *应付职工薪酬
     */
    @TableField(value = "flow_pay_salary")
    private String flowPaySalary ;

    /**
     *应交税费
     */
    @TableField(value = "flow_pay_tax")
    private String flowPayTax ;

    /**
     *其他应付款合计
     */
    @TableField(value = "flow_pay_other")
    private String flowPayOther ;

    /**
     *一年内到期的非流动负债
     */
    @TableField(value = "flow_sort_debt")
    private String flowSortDebt ;

    /**
     *其他流动负债
     */
    @TableField(value = "flow_other")
    private String flowOther ;

    /**
     *总非流动负债
     */
    @TableField(value = "total_unflow_debt")
    private String totalUnflowDebt ;

    /**
     *长期借款
     */
    @TableField(value = "unflow_long_loan")
    private String unflowLongLoan ;

    /**
     *长期应付款
     */
    @TableField(value = "unflow_pay_long")
    private String unflowPayLong ;

    /**
     *长期应付职工薪酬
     */
    @TableField(value = "unflow_pay_salary")
    private String unflowPaySalary ;

    /**
     *专项应付款
     */
    @TableField(value = "unflow_pay_special")
    private String unflowPaySpecial ;

    /**
     *预计负债
     */
    @TableField(value = "unflow_estimate_loan")
    private String unflowEstimateLoan ;

    /**
     *递延收益
     */
    @TableField(value = "unflow_deferred_profit")
    private String unflowDeferredProfit ;

    /**
     *递延所得税负债
     */
    @TableField(value = "unflow_deferred_tax_debt")
    private String unflowDeferredTaxDebt ;

    /**
     *其他非流动负债
     */
    @TableField(value = "unflow_other_debt")
    private String unflowOtherDebt ;
}
