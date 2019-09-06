package com.sky.vo;

import com.sky.core.model.VoModel;

/**
 * Created by ThinkPad on 2019/9/5.
 */
public class StockNoticeCompany_VO extends VoModel {

    private String stockCode ;

    private String stockName ;

    private String stockPlate ;

    private String companyRegion ;

    private String middleContrySector ;

    private String stockSector ;

    private String establishDate ;

    private String companyRegistMoney ;

    private String publishDate ;

    private String publishAmount ;

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

    public String getStockPlate() {
        return stockPlate;
    }

    public void setStockPlate(String stockPlate) {
        this.stockPlate = stockPlate;
    }

    public String getCompanyRegion() {
        return companyRegion;
    }

    public void setCompanyRegion(String companyRegion) {
        this.companyRegion = companyRegion;
    }

    public String getMiddleContrySector() {
        return middleContrySector;
    }

    public void setMiddleContrySector(String middleContrySector) {
        this.middleContrySector = middleContrySector;
    }

    public String getStockSector() {
        return stockSector;
    }

    public void setStockSector(String stockSector) {
        this.stockSector = stockSector;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getCompanyRegistMoney() {
        return companyRegistMoney;
    }

    public void setCompanyRegistMoney(String companyRegistMoney) {
        this.companyRegistMoney = companyRegistMoney;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishAmount() {
        return publishAmount;
    }

    public void setPublishAmount(String publishAmount) {
        this.publishAmount = publishAmount;
    }
}
