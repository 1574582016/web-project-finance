package com.sky.vo;

import com.sky.core.model.VoModel;

/**
 * Created by ThinkPad on 2019/5/5.
 */
public class StockStatisticsEchart_VO extends VoModel {

    private String DateTime ;

    private Integer DateType ;

    private String countNum ;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public Integer getDateType() {
        return DateType;
    }

    public void setDateType(Integer dateType) {
        DateType = dateType;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }
}
