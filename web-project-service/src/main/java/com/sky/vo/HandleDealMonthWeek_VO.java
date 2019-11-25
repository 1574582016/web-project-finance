package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/25.
 */
@Data
public class HandleDealMonthWeek_VO extends VoModel {

    /**
     * 周期类型
     */
    private Integer dealPeriod ;

    /**
     * 年
     */
    private Integer pointYear ;

    /**
     * 月
     */
    private Integer pointMonth ;

    /**
     * 周
     */
    private Integer pointWeek ;

    /**
     * 日
     */
    private Integer pointDay ;

    /**
     * 时间
     */
    private String startTime ;

    /**
     * 时间
     */
    private String endTime ;

    /**
     * 上市代码
     */
    private String stock_code ;

    private BigDecimal oneRise ;

    private BigDecimal towRise ;

    private BigDecimal threeRise ;

    private BigDecimal fourRise ;

    private BigDecimal fiveRise ;

    private BigDecimal sixRise ;

    private BigDecimal sevenRise ;

    private BigDecimal eightRise ;

    private BigDecimal nineRise ;

    private BigDecimal tenRise ;

    private BigDecimal elevenRise ;

    private BigDecimal twelveRise ;

    private BigDecimal oneAmplitude ;

    private BigDecimal towAmplitude ;

    private BigDecimal threeAmplitude ;

    private BigDecimal fourAmplitude ;

    private BigDecimal fiveAmplitude ;

    private BigDecimal sixAmplitude ;

    private BigDecimal sevenAmplitude ;

    private BigDecimal eightAmplitude ;

    private BigDecimal nineAmplitude ;

    private BigDecimal tenAmplitude ;

    private BigDecimal elevenAmplitude ;

    private BigDecimal twelveAmplitude ;

    private BigDecimal oneShock ;

    private BigDecimal towShock ;

    private BigDecimal threeShock ;

    private BigDecimal fourShock ;

    private BigDecimal fiveShock ;

    private BigDecimal sixShock ;

    private BigDecimal sevenShock ;

    private BigDecimal eightShock ;

    private BigDecimal nineShock ;

    private BigDecimal tenShock ;

    private BigDecimal elevenShock ;

    private BigDecimal twelveShock ;
}
