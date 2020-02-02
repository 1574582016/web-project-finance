package com.sky.vo;

import com.alibaba.fastjson.JSONArray;
import com.sky.core.model.VoModel;
import com.sky.model.StockCompanyProduct;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/12/14.
 */
@Data
public class CreateCompanyWorld_VO extends VoModel {

    private String forthSector ;

    private String fiveSector ;

    private String stockCode ;

    private String stockName ;

    private String companyName ;

    private String companyClass ;

    private String establishDay ;

    private String publishDay ;

    private String spaceYear ;

    private String publishCount ;

    private String flowCount ;

    private String flowRate ;

    private String marketOrder ;

    private String sectorOrder ;

    private String companyType ;

    private String companyRegion ;

    private String companyStrength ;

    private String companyChance ;

    private String marketClass ;

    private String internalOrganize ;

    private String focusOrganize ;

    private String groupHot ;

    private String companyLevel ;

    private String financialLevel ;

    private String companyWebsite ;

    private String mainBusiness ;

    private String marketType ;

    private List<StockCompanyProduct> productList ;

    private List<StockCompanyProduct> regionList ;

    private Map<String,Object> profitMap ;

    private Map<String,Object> assetMap ;

    private Map<String,JSONArray> cycleMap ;

    private Map<String,JSONArray> cycleTenMap ;

    private Integer rowSpan ;
}
