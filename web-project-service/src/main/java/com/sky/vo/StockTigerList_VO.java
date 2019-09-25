package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/25.
 */
@Data
public class StockTigerList_VO extends VoModel {

    private Integer id ;

    private String publishTime ;

    private String stockCode ;

    private String stockName ;

    private String stockPlate ;

    private BigDecimal upperRange ;

    private BigDecimal handRate ;

    private BigDecimal buyMoney ;

    private BigDecimal sellMoney ;

    private BigDecimal purBuyMoney ;

    private String focusReason ;

    private String bigContrySector ;

    private String middleContrySector ;

    private String stockSector ;

    private String unusualReason ;

    private String chooseReason ;
}
