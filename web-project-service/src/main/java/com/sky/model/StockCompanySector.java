package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/14.
 */
@Data
@TableName("stock_company_sector")
public class StockCompanySector extends BaseModel<StockCompanySector> {

    /**
     *股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *股票名称
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     *一级行业
     */
    @TableField("first_sector")
    private String firstSector ;

    /**
     *二级行业
     */
    @TableField("second_sector")
    private String secondSector ;

    /**
     *三级行业
     */
    @TableField("third_secotor")
    private String thirdSecotor ;

    /**
     *四级行业
     */
    @TableField("forth_sector")
    private String forthSector ;

    /**
     *五级行业
     */
    @TableField("five_sector")
    private String fiveSector ;

    /**
     *主营业务
     */
    @TableField("main_business")
    private String mainBusiness ;

    /**
     *公司简介
     */
    @TableField("company_intr")
    private String companyIntr ;

    /**
     *公司产品
     */
    @TableField("company_product")
    private String companyProduct ;

    /**
     *盈利分数
     */
    @TableField("profit_score")
    private BigDecimal profitScore ;

    /**
     *盈利增长分数
     */
    @TableField("profit_grow_score")
    private BigDecimal profitGrowScore ;

    /**
     *资产分数
     */
    @TableField("asset_score")
    private BigDecimal assetScore ;

    /**
     *资产增长分数
     */
    @TableField("asset_grow_score")
    private BigDecimal assetGrowScore ;

    /**
     *流动性分数
     */
    @TableField("flow_score")
    private BigDecimal flowScore ;

    /**
     *成立时间
     */
    @TableField("build_time")
    private String buildTime ;

    /**
     *上市时间
     */
    @TableField("publish_time")
    private String publishTime ;
}
