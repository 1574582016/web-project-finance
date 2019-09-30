package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/30.
 */
@Data
public class FestivalStatic_VO extends VoModel {

    private String sectorCode ;

    private String sectorName ;

    private BigDecimal increaseNum ;

    private BigDecimal decreaseNum ;

    private BigDecimal totalNum ;

    private BigDecimal increaseRate ;

    private BigDecimal decreaseRate ;

    private BigDecimal changeAverage ;

    private BigDecimal shockAverage ;

    private BigDecimal firstIncrease ;

    private BigDecimal secondIncrease ;

    private BigDecimal thirdIncrease ;

    private BigDecimal fourthIncrease ;

    private BigDecimal fifthIncrease ;

    private BigDecimal sixthIncrease ;
}
