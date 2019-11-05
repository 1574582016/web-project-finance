package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Data
public class CompanySectorVO extends VoModel {

    private String forthSector ;

    private String fiveSector;

    private String stockCode ;

    private String stockName ;

    private String companyLevel ;

    private String companyName ;

    private String companyRegion ;

    private String establishDate ;

    private String publishDate ;

    private String groupIndex ;

    private String groupHot ;

    private String mainBusiness ;

    private String financialLevel;
}
