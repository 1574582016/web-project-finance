package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/19.
 */
@Data
public class HotSectorStaticVO  extends VoModel {

    private String lastCode ;

    private String lastName ;

    private String nowCode ;

    private String nowName ;

    private BigDecimal amount ;

    private BigDecimal rate ;
}
