package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2020/1/2.
 */
@Data
public class StockInvestor_VO  extends VoModel {

    private String typeCode ;

    private String typeName ;

    private String staticTime ;

    private BigDecimal investorNum ;

    private BigDecimal investCount ;
}
