package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/18.
 */
@Data
public class MessageStatic_VO extends VoModel {

    private String title ;

    private Integer count ;

    private Integer timeType ;


    private Integer directType ;


    private BigDecimal staticNum ;
}
