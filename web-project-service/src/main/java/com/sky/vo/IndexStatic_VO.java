package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/20.
 */
@Data
public class IndexStatic_VO extends VoModel {

    private String pointTime ;

    private Integer increaseNum ;

    private Integer decreaseNum ;

    private Integer totalNum ;

    private BigDecimal increaseRate ;

    private BigDecimal decreaseRate ;

    private BigDecimal changeAverage ;

    private BigDecimal shockAverage ;
}