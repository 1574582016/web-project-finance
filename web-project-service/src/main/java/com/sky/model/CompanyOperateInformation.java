package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/5/11.
 */
@TableName("company_operate_information")
public class CompanyOperateInformation extends BaseModel<CompanyOperateInformation> {

    /**
     * 上市代码
     */
    @TableField("list_code")
    private String listCode ;

    /**
     * 数据发布时间
     */
    @TableField("publish_date")
    private String publishDate ;

    /**
     * 经营分类1—行业分类;2—产品分类;3-地域分类
     */
    @TableField("operate_type")
    private Integer operateType ;

    /**
     * 类型名称
     */
    @TableField("type_name")
    private String typeName ;

    /**
     * 主营收入
     */
    @TableField("operate_revenue")
    private BigDecimal operateRevenue ;

    /**
     * 主营收入率
     */
    @TableField("operate_revenue_rate")
    private BigDecimal operateRevenueRate ;

    /**
     * 主营成本
     */
    @TableField("operate_cost")
    private BigDecimal operateCost ;

    /**
     * 主营成本率
     */
    @TableField("operate_cost_rate")
    private BigDecimal operateCostRate ;

    /**
     * 主营利润
     */
    @TableField("operate_profit")
    private BigDecimal operateProfit ;

    /**
     * 主营利润率
     */
    @TableField("operate_profit_rate")
    private BigDecimal operateProfitRate ;

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getOperateRevenue() {
        return operateRevenue;
    }

    public void setOperateRevenue(BigDecimal operateRevenue) {
        this.operateRevenue = operateRevenue;
    }

    public BigDecimal getOperateRevenueRate() {
        return operateRevenueRate;
    }

    public void setOperateRevenueRate(BigDecimal operateRevenueRate) {
        this.operateRevenueRate = operateRevenueRate;
    }

    public BigDecimal getOperateCost() {
        return operateCost;
    }

    public void setOperateCost(BigDecimal operateCost) {
        this.operateCost = operateCost;
    }

    public BigDecimal getOperateCostRate() {
        return operateCostRate;
    }

    public void setOperateCostRate(BigDecimal operateCostRate) {
        this.operateCostRate = operateCostRate;
    }

    public BigDecimal getOperateProfit() {
        return operateProfit;
    }

    public void setOperateProfit(BigDecimal operateProfit) {
        this.operateProfit = operateProfit;
    }

    public BigDecimal getOperateProfitRate() {
        return operateProfitRate;
    }

    public void setOperateProfitRate(BigDecimal operateProfitRate) {
        this.operateProfitRate = operateProfitRate;
    }
}
