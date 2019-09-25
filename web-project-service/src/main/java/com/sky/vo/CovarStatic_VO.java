package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/25.
 */
@Data
public class CovarStatic_VO extends VoModel {

    private String staticCode ;

    private String staticName ;

    private BigDecimal upperRelevant ;
}
