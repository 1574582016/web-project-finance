package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/28.
 */
@Data
public class MacroEconomy_VO extends VoModel {

    private String publishMonth;

    private String publishWeek;

    private String publishDay ;

    private String className ;

    private BigDecimal publishValue ;
}
