package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/25.
 */
@Data
public class CovarDeal_VO extends VoModel {

    private String dealTime ;

    private BigDecimal isUpper ;

    private BigDecimal upperPrice ;

    private BigDecimal sockPrice ;
}
