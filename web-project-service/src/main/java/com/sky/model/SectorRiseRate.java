package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Data
@TableName("sector_rise_rate")
public class SectorRiseRate extends BaseModel<SectorRiseRate> {

    /**
     * 周期类型
     */
    @TableField("deal_period")
    private Integer dealPeriod ;

    /**
     * 年
     */
    @TableField("point_year")
    private Integer pointYear ;

    /**
     * 月
     */
    @TableField("point_month")
    private Integer pointMonth ;

    /**
     * 周
     */
    @TableField("point_week")
    private Integer pointWeek ;

    /**
     * 日
     */
    @TableField("point_day")
    private Integer pointDay ;

    /**
     * 时间
     */
    @TableField("start_time")
    private String startTime ;

    /**
     * 时间
     */
    @TableField("end_time")
    private String endTime ;

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


    @TableField("one_rise")
    private BigDecimal oneRise ;


    @TableField("tow_rise")
    private BigDecimal towRise ;


    @TableField("three_rise")
    private BigDecimal threeRise ;


    @TableField("four_rise")
    private BigDecimal fourRise ;


    @TableField("five_rise")
    private BigDecimal fiveRise ;


    @TableField("six_rise")
    private BigDecimal sixRise ;


    @TableField("seven_rise")
    private BigDecimal sevenRise ;


    @TableField("eight_rise")
    private BigDecimal eightRise ;


    @TableField("nine_rise")
    private BigDecimal nineRise ;


    @TableField("ten_rise")
    private BigDecimal tenRise ;


    @TableField("eleven_rise")
    private BigDecimal elevenRise ;


    @TableField("twelve_rise")
    private BigDecimal twelveRise ;



    @TableField("one_amplitude")
    private BigDecimal oneAmplitude ;


    @TableField("tow_amplitude")
    private BigDecimal towAmplitude ;


    @TableField("three_amplitude")
    private BigDecimal threeAmplitude ;


    @TableField("four_amplitude")
    private BigDecimal fourAmplitude ;


    @TableField("five_amplitude")
    private BigDecimal fiveAmplitude ;


    @TableField("six_amplitude")
    private BigDecimal sixAmplitude ;


    @TableField("seven_amplitude")
    private BigDecimal sevenAmplitude ;


    @TableField("eight_amplitude")
    private BigDecimal eightAmplitude ;


    @TableField("nine_amplitude")
    private BigDecimal nineAmplitude ;


    @TableField("ten_amplitude")
    private BigDecimal tenAmplitude ;


    @TableField("eleven_amplitude")
    private BigDecimal elevenAmplitude ;


    @TableField("twelve_amplitude")
    private BigDecimal twelveAmplitude ;



    @TableField("one_shock")
    private BigDecimal oneShock ;


    @TableField("tow_shock")
    private BigDecimal towShock ;


    @TableField("three_shock")
    private BigDecimal threeShock ;


    @TableField("four_shock")
    private BigDecimal fourShock ;


    @TableField("five_shock")
    private BigDecimal fiveShock ;


    @TableField("six_shock")
    private BigDecimal sixShock ;


    @TableField("seven_shock")
    private BigDecimal sevenShock ;


    @TableField("eight_shock")
    private BigDecimal eightShock ;


    @TableField("nine_shock")
    private BigDecimal nineShock ;


    @TableField("ten_shock")
    private BigDecimal tenShock ;


    @TableField("eleven_shock")
    private BigDecimal elevenShock ;


    @TableField("twelve_shock")
    private BigDecimal twelveShock ;

}
