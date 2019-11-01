package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/1.
 */
@Data
public class WeekLossJobCountVO extends VoModel {

    private String publishMonth ;

    private String className ;

    private BigDecimal firstWeekValue ;

    private BigDecimal secondWeekValue ;

    private BigDecimal thirdWeekValue ;

    private BigDecimal forthWeekValue ;

    private BigDecimal fifthWeekValue ;
}
