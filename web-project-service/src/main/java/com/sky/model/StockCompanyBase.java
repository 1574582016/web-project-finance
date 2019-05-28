package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@TableName("stock_company_base")
public class StockCompanyBase extends BaseModel<StockCompanyBase> {

    /**
     *公司名称
     */
    @TableField("company_name")
    private String companyName ;

    /**
     *英文名称
     */
    @TableField("english_name")
    private String englishName ;

    /**
     *曾用名称
     */
    @TableField("last_name")
    private String lastName ;

    /**
     *A股代码
     */
    @TableField("stock_a_code")
    private String stockACode ;

    /**
     *A股简称
     */
    @TableField("stock_a_name")
    private String stockAName ;

    /**
     *B股代码
     */
    @TableField("stock_b_code")
    private String stockBCode ;

    /**
     *B股简称
     */
    @TableField("stock_b_name")
    private String stockBName ;

    /**
     *H股代码
     */
    @TableField("stock_h_code")
    private String stockHCode ;

    /**
     *H股简称
     */
    @TableField("stock_h_name")
    private String stockHName ;

    /**
     *证券板块类别
     */
    @TableField("stock_plate")
    private String stockPlate ;

    /**
     *证券行业
     */
    @TableField("stock_sector")
    private String stockSector ;

    /**
     *国家行业
     */
    @TableField("contry_sector")
    private String contrySector ;

    /**
     *上市交易所
     */
    @TableField("stock_exchange")
    private String stockExchange ;

    /**
     *总经理
     */
    @TableField("company_manager")
    private String companyManager ;

    /**
     *法人代表
     */
    @TableField("company_legal_person")
    private String companyLegalPerson ;

    /**
     *董秘
     */
    @TableField("company_chairman_secretary")
    private String companyChairmanSecretary ;

    /**
     *董事长
     */
    @TableField("company_chairman")
    private String companyChairman ;

    /**
     *证券事务代表
     */
    @TableField("company_stock_represent")
    private String companyStockRepresent ;

    /**
     *独立董事
     */
    @TableField("company_independent_director")
    private String companyIndependentDirector ;

    /**
     *联系电话
     */
    @TableField("company_telephone")
    private String companyTelephone ;

    /**
     *电子信箱
     */
    @TableField("company_email")
    private String companyEmail ;

    /**
     *传真
     */
    @TableField("company_fax")
    private String companyFax ;

    /**
     *邮政编码
     */
    @TableField("company_post_code")
    private String companyPostCode ;

    /**
     *公司网址
     */
    @TableField("company_website")
    private String companyWebsite ;

    /**
     *办公地址
     */
    @TableField("company_address")
    private String companyAddress ;

    /**
     *注册地址
     */
    @TableField("company_regist_address")
    private String companyRegistAddress ;

    /**
     *区域
     */
    @TableField("company_region")
    private String companyRegion ;

    /**
     *注册资本(元)
     */
    @TableField("company_regist_money")
    private String companyRegistMoney ;

    /**
     *工商登记
     */
    @TableField("industry_commerce_code")
    private String industryCommerceCode ;

    /**
     *雇员人数
     */
    @TableField("company_employee")
    private String companyEmployee ;

    /**
     *管理人员人数
     */
    @TableField("company_employer")
    private String companyEmployer ;

    /**
     *律师事务所
     */
    @TableField("law_firm")
    private String lawFirm ;

    /**
     *会计师事务所
     */
    @TableField("accounting_firm")
    private String accountingFirm ;

    /**
     *成立日期
     */
    @TableField("establish_date")
    private String establishDate ;

    /**
     *上市日期
     */
    @TableField("publish_date")
    private String publishDate ;

    /**
     *发行市盈率(倍)
     */
    @TableField("publish_pe_ratio")
    private String publishPERatio ;

    /**
     *网上发行日期
     */
    @TableField("web_publish_date")
    private String webPublishDate ;

    /**
     *发行方式
     */
    @TableField("publish_type")
    private String publishType ;

    /**
     *每股面值(元)
     */
    @TableField("publish_face_value")
    private String publishFaceValue ;

    /**
     *发行量(股)
     */
    @TableField("publish_amount")
    private String publishAmount ;

    /**
     *每股发行价(元)
     */
    @TableField("publish_price")
    private String publishPrice ;

    /**
     *发行费用(元)
     */
    @TableField("publish_cost")
    private String publishCost ;

    /**
     *发行总市值(元)
     */
    @TableField("publish_market_value")
    private String publishMarketValue ;

    /**
     *募集资金净额(元)
     */
    @TableField("publish_raise_fund")
    private String publishRaiseFund ;

    /**
     *首日开盘价(元)
     */
    @TableField("first_start_price")
    private String firstStartPrice ;

    /**
     *首日收盘价(元)
     */
    @TableField("first_close_price")
    private String firstClosePrice ;

    /**
     *首日换手率
     */
    @TableField("first_turnover_rate")
    private String firstTurnoverRate ;

    /**
     *首日最高价(元)
     */
    @TableField("first_high_price")
    private String firstHighPrice ;

    /**
     *网下配售中签率
     */
    @TableField("off_line_distribute_rate")
    private String offLineDistributeRate ;

    /**
     *定价中签率
     */
    @TableField("price_win_rate")
    private String priceWinRate ;

    /**
     *经营范围
     */
    @TableField("company_business_scope")
    private String companyBusinessScope ;

    /**
     *题材
     */
    @TableField("company_subject_matter")
    private String companySubjectMatter ;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStockACode() {
        return stockACode;
    }

    public void setStockACode(String stockACode) {
        this.stockACode = stockACode;
    }

    public String getStockAName() {
        return stockAName;
    }

    public void setStockAName(String stockAName) {
        this.stockAName = stockAName;
    }

    public String getStockBCode() {
        return stockBCode;
    }

    public void setStockBCode(String stockBCode) {
        this.stockBCode = stockBCode;
    }

    public String getStockBName() {
        return stockBName;
    }

    public void setStockBName(String stockBName) {
        this.stockBName = stockBName;
    }

    public String getStockHCode() {
        return stockHCode;
    }

    public void setStockHCode(String stockHCode) {
        this.stockHCode = stockHCode;
    }

    public String getStockHName() {
        return stockHName;
    }

    public void setStockHName(String stockHName) {
        this.stockHName = stockHName;
    }

    public String getStockPlate() {
        return stockPlate;
    }

    public void setStockPlate(String stockPlate) {
        this.stockPlate = stockPlate;
    }

    public String getStockSector() {
        return stockSector;
    }

    public void setStockSector(String stockSector) {
        this.stockSector = stockSector;
    }

    public String getContrySector() {
        return contrySector;
    }

    public void setContrySector(String contrySector) {
        this.contrySector = contrySector;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getCompanyManager() {
        return companyManager;
    }

    public void setCompanyManager(String companyManager) {
        this.companyManager = companyManager;
    }

    public String getCompanyLegalPerson() {
        return companyLegalPerson;
    }

    public void setCompanyLegalPerson(String companyLegalPerson) {
        this.companyLegalPerson = companyLegalPerson;
    }

    public String getCompanyChairmanSecretary() {
        return companyChairmanSecretary;
    }

    public void setCompanyChairmanSecretary(String companyChairmanSecretary) {
        this.companyChairmanSecretary = companyChairmanSecretary;
    }

    public String getCompanyChairman() {
        return companyChairman;
    }

    public void setCompanyChairman(String companyChairman) {
        this.companyChairman = companyChairman;
    }

    public String getCompanyStockRepresent() {
        return companyStockRepresent;
    }

    public void setCompanyStockRepresent(String companyStockRepresent) {
        this.companyStockRepresent = companyStockRepresent;
    }

    public String getCompanyIndependentDirector() {
        return companyIndependentDirector;
    }

    public void setCompanyIndependentDirector(String companyIndependentDirector) {
        this.companyIndependentDirector = companyIndependentDirector;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyPostCode() {
        return companyPostCode;
    }

    public void setCompanyPostCode(String companyPostCode) {
        this.companyPostCode = companyPostCode;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyRegistAddress() {
        return companyRegistAddress;
    }

    public void setCompanyRegistAddress(String companyRegistAddress) {
        this.companyRegistAddress = companyRegistAddress;
    }

    public String getCompanyRegion() {
        return companyRegion;
    }

    public void setCompanyRegion(String companyRegion) {
        this.companyRegion = companyRegion;
    }

    public String getCompanyRegistMoney() {
        return companyRegistMoney;
    }

    public void setCompanyRegistMoney(String companyRegistMoney) {
        this.companyRegistMoney = companyRegistMoney;
    }

    public String getIndustryCommerceCode() {
        return industryCommerceCode;
    }

    public void setIndustryCommerceCode(String industryCommerceCode) {
        this.industryCommerceCode = industryCommerceCode;
    }

    public String getCompanyEmployee() {
        return companyEmployee;
    }

    public void setCompanyEmployee(String companyEmployee) {
        this.companyEmployee = companyEmployee;
    }

    public String getCompanyEmployer() {
        return companyEmployer;
    }

    public void setCompanyEmployer(String companyEmployer) {
        this.companyEmployer = companyEmployer;
    }

    public String getLawFirm() {
        return lawFirm;
    }

    public void setLawFirm(String lawFirm) {
        this.lawFirm = lawFirm;
    }

    public String getAccountingFirm() {
        return accountingFirm;
    }

    public void setAccountingFirm(String accountingFirm) {
        this.accountingFirm = accountingFirm;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishPERatio() {
        return publishPERatio;
    }

    public void setPublishPERatio(String publishPERatio) {
        this.publishPERatio = publishPERatio;
    }

    public String getWebPublishDate() {
        return webPublishDate;
    }

    public void setWebPublishDate(String webPublishDate) {
        this.webPublishDate = webPublishDate;
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public String getPublishFaceValue() {
        return publishFaceValue;
    }

    public void setPublishFaceValue(String publishFaceValue) {
        this.publishFaceValue = publishFaceValue;
    }

    public String getPublishAmount() {
        return publishAmount;
    }

    public void setPublishAmount(String publishAmount) {
        this.publishAmount = publishAmount;
    }

    public String getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(String publishPrice) {
        this.publishPrice = publishPrice;
    }

    public String getPublishCost() {
        return publishCost;
    }

    public void setPublishCost(String publishCost) {
        this.publishCost = publishCost;
    }

    public String getPublishMarketValue() {
        return publishMarketValue;
    }

    public void setPublishMarketValue(String publishMarketValue) {
        this.publishMarketValue = publishMarketValue;
    }

    public String getPublishRaiseFund() {
        return publishRaiseFund;
    }

    public void setPublishRaiseFund(String publishRaiseFund) {
        this.publishRaiseFund = publishRaiseFund;
    }

    public String getFirstStartPrice() {
        return firstStartPrice;
    }

    public void setFirstStartPrice(String firstStartPrice) {
        this.firstStartPrice = firstStartPrice;
    }

    public String getFirstClosePrice() {
        return firstClosePrice;
    }

    public void setFirstClosePrice(String firstClosePrice) {
        this.firstClosePrice = firstClosePrice;
    }

    public String getFirstTurnoverRate() {
        return firstTurnoverRate;
    }

    public void setFirstTurnoverRate(String firstTurnoverRate) {
        this.firstTurnoverRate = firstTurnoverRate;
    }

    public String getFirstHighPrice() {
        return firstHighPrice;
    }

    public void setFirstHighPrice(String firstHighPrice) {
        this.firstHighPrice = firstHighPrice;
    }

    public String getOffLineDistributeRate() {
        return offLineDistributeRate;
    }

    public void setOffLineDistributeRate(String offLineDistributeRate) {
        this.offLineDistributeRate = offLineDistributeRate;
    }

    public String getPriceWinRate() {
        return priceWinRate;
    }

    public void setPriceWinRate(String priceWinRate) {
        this.priceWinRate = priceWinRate;
    }

    public String getCompanyBusinessScope() {
        return companyBusinessScope;
    }

    public void setCompanyBusinessScope(String companyBusinessScope) {
        this.companyBusinessScope = companyBusinessScope;
    }

    public String getCompanySubjectMatter() {
        return companySubjectMatter;
    }

    public void setCompanySubjectMatter(String companySubjectMatter) {
        this.companySubjectMatter = companySubjectMatter;
    }
}
