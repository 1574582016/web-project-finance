package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/5.
 */
@Data
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

    private List<StockNoticeDetail_VO> detailVoList ;

}
