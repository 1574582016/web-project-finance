package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Data
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
    @TableField("big_contry_sector")
    private String bigContrySector ;

    /**
     *国家行业
     */
    @TableField("middle_contry_sector")
    private String middleContrySector ;

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

}
