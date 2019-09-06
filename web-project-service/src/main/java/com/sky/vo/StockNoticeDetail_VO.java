package com.sky.vo;

import com.sky.core.model.VoModel;

/**
 * Created by ThinkPad on 2019/9/6.
 */
public class StockNoticeDetail_VO extends VoModel {

    private String stockCode ;

    private String stockName ;

    private String publishTime ;

    private String classLevel ;

    private String bigNoticeClass ;

    private String middleNoticeClass ;

    private String noticeType ;

    private String noticeTitle ;

    private String noticeDetail ;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getBigNoticeClass() {
        return bigNoticeClass;
    }

    public void setBigNoticeClass(String bigNoticeClass) {
        this.bigNoticeClass = bigNoticeClass;
    }

    public String getMiddleNoticeClass() {
        return middleNoticeClass;
    }

    public void setMiddleNoticeClass(String middleNoticeClass) {
        this.middleNoticeClass = middleNoticeClass;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDetail() {
        return noticeDetail;
    }

    public void setNoticeDetail(String noticeDetail) {
        this.noticeDetail = noticeDetail;
    }
}
