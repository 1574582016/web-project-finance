package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/6.
 */
@Data
@TableName("stock_company_value_compare")
public class StockCompanyValueCompare extends BaseModel<StockCompanyValueCompare> {

    /**
     *统计年份
     */
    @TableField("static_year")
    private String staticYear ;

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
     *行业编码
     */
    @TableField("sector_code")
    private String sectorCode ;

    /**
     *行业名称
     */
    @TableField("sector_name")
    private String sectorName ;

    /**
     *发行股本
     */
    @TableField("publish_count")
    private String publishCount ;

    /**
     *流通股本
     */
    @TableField("flow_count")
    private String flowCount ;

    /**
     *总市值
     */
    @TableField("publish_value")
    private String publishValue ;

    /**
     *流通市值
     */
    @TableField("flow_value")
    private String flowValue ;

    /**
     *当前价格
     */
    @TableField("current_price")
    private String currentPrice ;

    /**
     *企业总数
     */
    @TableField("company_count")
    private String companyCount ;

    /**
     *行业平均市值
     */
    @TableField("sector_publish_value_average")
    private String sectorPublishValueAverage ;

    /**
     *行业市值排名
     */
    @TableField("sector_publish_value_order")
    private String sectorPublishValueOrder ;

    /**
     *净资产
     */
    @TableField("net_assets")
    private String netAssets ;

    /**
     *行业平均净资产
     */
    @TableField("sector_net_assets_average")
    private String sectorNetAssetsAverage ;

    /**
     *行业净资产排名
     */
    @TableField("sector_net_assets_order")
    private String sectorNetAssetsOrder ;

    /**
     *净利润
     */
    @TableField("net_profit")
    private String netProfit ;

    /**
     *行业平均净利润
     */
    @TableField("sector_net_profit_average")
    private String sectorNetProfitAverage ;

    /**
     *行业净利润排名
     */
    @TableField("sector_net_profit_order")
    private String sectorNetProfitOrder ;

    /**
     *市盈率%
     */
    @TableField("pe_ratio")
    private String peRatio ;

    /**
     *行业平均市盈率%
     */
    @TableField("sector_pe_ratio_average")
    private String sectorPeRatioAverage ;

    /**
     *行业市盈率排名
     */
    @TableField("sector_pe_ratio_order")
    private String sectorPeRatioOrder ;

    /**
     *市净率%
     */
    @TableField("pb_ratio")
    private String pbRatio ;

    /**
     *行业平均市净率%
     */
    @TableField("sector_pb_ratio_average")
    private String sectorPbRatioAverage ;

    /**
     *行业市净率排名
     */
    @TableField("sector_pb_ratio_order")
    private String sectorPbRatioOrder ;

    /**
     *毛利率%
     */
    @TableField("gross_profit_ratio")
    private String grossProfitRatio ;

    /**
     *行业平均毛利率%
     */
    @TableField("sector_gross_profit_ratio_average")
    private String sectorGrossProfitRatioAverage ;

    /**
     *行业毛利率排名
     */
    @TableField("sector_gross_profit_ratio_order")
    private String sectorGrossProfitRatioOrder ;

    /**
     *净利率%
     */
    @TableField("net_profit_ratio")
    private String netProfitRatio ;

    /**
     *行业平均净利率%
     */
    @TableField("sector_net_profit_ratio_average")
    private String sectorNetProfitRatioAverage ;

    /**
     *行业净利率排名
     */
    @TableField("sector_net_profit_ratio_order")
    private String sectorNetProfitRatioOrder ;

    /**
     *净资产收益率%
     */
    @TableField("roe_ratio")
    private String roeRatio ;

    /**
     *行业平均净资产收益率%
     */
    @TableField("sector_roe_ratio_average")
    private String sectorRoeRatioAverage ;

    /**
     *行业净资产收益率排名
     */
    @TableField("sector_roe_ratio_order")
    private String sectorRoeRatioOrder ;

    /**
     *成长性排行
     */
    @TableField("grow_order")
    private String growOrder ;

    /**
     *每股收益增长率(%)
     */
    @TableField("grow_epsg")
    private String growEpsg ;

    /**
     *行业平均每股收益增长率(%)
     */
    @TableField("sector_grow_epsg_average")
    private String sectorGrowEpsgAverage ;

    /**
     *行业每股收益增长率中值
     */
    @TableField("sector_grow_epsg_middle")
    private String sectorGrowEpsgMiddle ;

    /**
     *营业收入增长率(%)
     */
    @TableField("grow_revinr")
    private String growRevinr ;

    /**
     *行业平均营业收入增长率(%)
     */
    @TableField("sector_grow_revinr_average")
    private String sectorGrowRevinrAverage ;

    /**
     *行业营业收入增长率中值
     */
    @TableField("sector_grow_revinr_middle")
    private String sectorGrowRevinrMiddle ;

    /**
     *净利润增长率(%)
     */
    @TableField("grow_npgr")
    private String growNpgr ;

    /**
     *行业平均净利润增长率(%)
     */
    @TableField("sector_grow_npgr_average")
    private String sectorGrowNpgrAverage ;

    /**
     *行业净利润增长率中值
     */
    @TableField("sector_grow_npgr_middle")
    private String sectorGrowNpgrMiddle ;

    /**
     *估值排行
     */
    @TableField("appraise_order")
    private String appraiseOrder ;

    /**
     *盈利增长速度
     */
    @TableField("appraise_peg")
    private String appraisePeg ;

    /**
     *行业平均盈利增长速度
     */
    @TableField("sector_appraise_peg_average")
    private String sectorAppraisePegAverage ;

    /**
     *行业盈利增长速度中值
     */
    @TableField("sector_appraise_peg_middle")
    private String sectorAppraisePegMiddle ;

    /**
     *杜邦排行
     */
    @TableField("dupont_order")
    private String dupontOrder ;

    /**
     *净资产收益率(%)
     */
    @TableField("dupont_roe")
    private String dupontRoe ;

    /**
     *行业平均净资产收益率
     */
    @TableField("sector_dupont_roe_average")
    private String sectorDupontRoeAverage ;

    /**
     *行业净资产收益率中值
     */
    @TableField("sector_dupont_roe_middle")
    private String sectorDupontRoeMiddle ;

    /**
     *净利率(%)
     */
    @TableField("dupont_net_profit_ratio")
    private String dupontNetProfitRatio ;

    /**
     *行业平均净利率
     */
    @TableField("sector_dupont_net_profit_ratio_average")
    private String sectorDupontNetProfitRatioAverage ;

    /**
     *行业净利率中值
     */
    @TableField("sector_dupont_net_profit_ratio_middle")
    private String sectorDupontNetProfitRatioMiddle ;

    /**
     *资产周转率(%)
     */
    @TableField("dupont_ato")
    private String dupontAto ;

    /**
     *行业平均资产周转率
     */
    @TableField("sector_dupont_ato_average")
    private String sectorDupontAtoAverage ;

    /**
     *行业资产周转率中值
     */
    @TableField("sector_dupont_ato_middle")
    private String sectorDupontAtoMiddle ;

    /**
     *权益乘数(%)
     */
    @TableField("dupont_em")
    private String dupontEm ;

    /**
     *行业平均权益乘数
     */
    @TableField("sector_dupont_em_average")
    private String sectorDupontEmAverage ;

    /**
     *行业权益乘数中值
     */
    @TableField("sector_dupont_em_middle")
    private String sectorDupontEmMiddle ;

    /**
     *公司规模排行
     */
    @TableField("scale_order")
    private String scaleOrder ;

    /**
     *总市值
     */
    @TableField("total_market_value")
    private String totalMarketValue ;

    /**
     *行业平均总市值
     */
    @TableField("total_market_value_average")
    private String totalMarketValueAverage ;

    /**
     *行业总市值中值
     */
    @TableField("total_market_value_middle")
    private String totalMarketValueMiddle ;

    /**
     *流通市值
     */
    @TableField("flow_market_value")
    private String flowMarketValue ;

    /**
     *行业平均流通市值
     */
    @TableField("flow_market_value_average")
    private String flowMarketValueAverage ;

    /**
     *行业流通市值中值
     */
    @TableField("flow_market_value_middle")
    private String flowMarketValueMiddle ;

    /**
     *营业收入
     */
    @TableField("business_total_profit")
    private String businessTotalProfit ;

    /**
     *行业平均营业收入
     */
    @TableField("business_total_profit_average")
    private String businessTotalProfitAverage ;

    /**
     *行业营业收入中值
     */
    @TableField("business_total_profit_middle")
    private String businessTotalProfitMiddle ;

    /**
     *净收入
     */
    @TableField("business_net_profit")
    private String businessNetProfit ;

    /**
     *行业平均净收入
     */
    @TableField("business_net_profit_average")
    private String businessNetProfitAverage ;

    /**
     *行业净收入中值
     */
    @TableField("business_net_profit_middle")
    private String businessNetProfitMiddle ;

    /**
     *主营产品
     */
    @TableField("business_product")
    private String businessProduct ;

}
