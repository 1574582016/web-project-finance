package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@TableName("company_base_information")
public class CompanyBaseInformation extends BaseModel<CompanyBaseInformation> {

    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName ;

    /**
     * 所属区域
     */
    @TableField("company_region")
    private String companyRegion ;

    /**
     * 成立时间
     */
    @TableField("establish_time")
    private String establishTime ;

    /**
     * 注册资本(万元)
     */
    @TableField("register_money")
    private String registerMoney ;

    /**
     * 公司行业
     */
    @TableField("company_industry")
    private String companyIndustry ;

    /**
     * 公司行业细分
     */
    @TableField("company_industry_detail")
    private String companyIndustryDetail ;

    /**
     * 运营地区1-华总地区;2-华东地区;3-华北地区;4-华南地区;5-东北地区;6-西北地区;7-西南地区
     */
    @TableField("business_area")
    private String businessArea ;

    /**
     * 上市代码
     */
    @TableField("list_code")
    private String listCode ;

    /**
     * 上市名称
     */
    @TableField("list_name")
    private String listName ;

    /**
     * 上市时间
     */
    @TableField("list_time")
    private String listTime ;

    /**
     * 证券市场:1—上交所;2-深交所
     */
    @TableField("list_market")
    private Integer listMarket ;

    /**
     * 所属板块:1—主板;2-中小板;3—创业板;4-风险警示板
     */
    @TableField("list_sector")
    private Integer listSector ;

    /**
     * 发现量(万股)
     */
    @TableField("list_number")
    private String listNumber ;

    /**
     * 发行价
     */
    @TableField("list_price")
    private BigDecimal listPrice ;

    /**
     * 发现市值(亿)
     */
    @TableField("list_money")
    private String listMoney ;

    /**
     * 市值排名
     */
    @TableField("list_order")
    private Integer listOrder ;

    /**
     * 发现市值(亿)
     */
    @TableField("list_industry")
    private String listIndustry ;

    /**
     * 主营行业
     */
    @TableField("main_relate_industry")
    private String mainRelateIndustry ;

    /**
     * 副营行业
     */
    @TableField("vice_relate_industry_one")
    private String viceRelateIndustryOne ;

    /**
     * 副营行业
     */
    @TableField("vice_relate_industry_two")
    private String viceRelateIndustryTwo ;

    /**
     * 副营行业
     */
    @TableField("vice_relate_industry_three")
    private String viceRelateIndustryThree ;

    /**
     * 副营行业
     */
    @TableField("vice_relate_industry_four")
    private String viceRelateIndustryFour ;

    /**
     * 副营行业
     */
    @TableField("vice_relate_industry_five")
    private String viceRelateIndustryFive ;

    /**
     * 主营产品
     */
    @TableField("main_produce_product")
    private String mainProduceProduct ;

    /**
     * 副营产品1
     */
    @TableField("vice_produce_product_one")
    private String viceProduceProductOne ;

    /**
     * 副营产品2
     */
    @TableField("vice_produce_product_two")
    private String viceProduceProductTwo ;

    /**
     * 副营产品3
     */
    @TableField("vice_produce_product_three")
    private String viceProduceProductThree ;

    /**
     * 副营产品4
     */
    @TableField("vice_produce_product_four")
    private String viceProduceProductFour ;

    /**
     * 副营产品5
     */
    @TableField("vice_produce_product_five")
    private String viceProduceProductFive ;

    /**
     * 副营产品6
     */
    @TableField("vice_produce_product_six")
    private String viceProduceProductSix ;

    /**
     * 副营产品7
     */
    @TableField("vice_produce_product_seven")
    private String viceProduceProductSeven ;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRegion() {
        return companyRegion;
    }

    public void setCompanyRegion(String companyRegion) {
        this.companyRegion = companyRegion;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyIndustryDetail() {
        return companyIndustryDetail;
    }

    public void setCompanyIndustryDetail(String companyIndustryDetail) {
        this.companyIndustryDetail = companyIndustryDetail;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListTime() {
        return listTime;
    }

    public void setListTime(String listTime) {
        this.listTime = listTime;
    }

    public Integer getListMarket() {
        return listMarket;
    }

    public void setListMarket(Integer listMarket) {
        this.listMarket = listMarket;
    }

    public Integer getListSector() {
        return listSector;
    }

    public void setListSector(Integer listSector) {
        this.listSector = listSector;
    }

    public String getListNumber() {
        return listNumber;
    }

    public void setListNumber(String listNumber) {
        this.listNumber = listNumber;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public String getListMoney() {
        return listMoney;
    }

    public void setListMoney(String listMoney) {
        this.listMoney = listMoney;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public String getListIndustry() {
        return listIndustry;
    }

    public void setListIndustry(String listIndustry) {
        this.listIndustry = listIndustry;
    }

    public String getMainRelateIndustry() {
        return mainRelateIndustry;
    }

    public void setMainRelateIndustry(String mainRelateIndustry) {
        this.mainRelateIndustry = mainRelateIndustry;
    }

    public String getViceRelateIndustryOne() {
        return viceRelateIndustryOne;
    }

    public void setViceRelateIndustryOne(String viceRelateIndustryOne) {
        this.viceRelateIndustryOne = viceRelateIndustryOne;
    }

    public String getViceRelateIndustryTwo() {
        return viceRelateIndustryTwo;
    }

    public void setViceRelateIndustryTwo(String viceRelateIndustryTwo) {
        this.viceRelateIndustryTwo = viceRelateIndustryTwo;
    }

    public String getViceRelateIndustryThree() {
        return viceRelateIndustryThree;
    }

    public void setViceRelateIndustryThree(String viceRelateIndustryThree) {
        this.viceRelateIndustryThree = viceRelateIndustryThree;
    }

    public String getViceRelateIndustryFour() {
        return viceRelateIndustryFour;
    }

    public void setViceRelateIndustryFour(String viceRelateIndustryFour) {
        this.viceRelateIndustryFour = viceRelateIndustryFour;
    }

    public String getViceRelateIndustryFive() {
        return viceRelateIndustryFive;
    }

    public void setViceRelateIndustryFive(String viceRelateIndustryFive) {
        this.viceRelateIndustryFive = viceRelateIndustryFive;
    }

    public String getMainProduceProduct() {
        return mainProduceProduct;
    }

    public void setMainProduceProduct(String mainProduceProduct) {
        this.mainProduceProduct = mainProduceProduct;
    }

    public String getViceProduceProductOne() {
        return viceProduceProductOne;
    }

    public void setViceProduceProductOne(String viceProduceProductOne) {
        this.viceProduceProductOne = viceProduceProductOne;
    }

    public String getViceProduceProductTwo() {
        return viceProduceProductTwo;
    }

    public void setViceProduceProductTwo(String viceProduceProductTwo) {
        this.viceProduceProductTwo = viceProduceProductTwo;
    }

    public String getViceProduceProductThree() {
        return viceProduceProductThree;
    }

    public void setViceProduceProductThree(String viceProduceProductThree) {
        this.viceProduceProductThree = viceProduceProductThree;
    }

    public String getViceProduceProductFour() {
        return viceProduceProductFour;
    }

    public void setViceProduceProductFour(String viceProduceProductFour) {
        this.viceProduceProductFour = viceProduceProductFour;
    }

    public String getViceProduceProductFive() {
        return viceProduceProductFive;
    }

    public void setViceProduceProductFive(String viceProduceProductFive) {
        this.viceProduceProductFive = viceProduceProductFive;
    }

    public String getViceProduceProductSix() {
        return viceProduceProductSix;
    }

    public void setViceProduceProductSix(String viceProduceProductSix) {
        this.viceProduceProductSix = viceProduceProductSix;
    }

    public String getViceProduceProductSeven() {
        return viceProduceProductSeven;
    }

    public void setViceProduceProductSeven(String viceProduceProductSeven) {
        this.viceProduceProductSeven = viceProduceProductSeven;
    }
}
