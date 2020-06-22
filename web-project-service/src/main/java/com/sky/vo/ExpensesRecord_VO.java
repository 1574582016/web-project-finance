package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/6/22.
 */
@Data
public class ExpensesRecord_VO extends VoModel {

    private String id ;

    private String firstName ;

    private String secondName ;

    private String expensesDay ;

    private String expensesMoney ;

}
