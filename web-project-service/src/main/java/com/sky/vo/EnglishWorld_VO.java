package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Data
public class EnglishWorld_VO extends VoModel {

    private Integer affixNum ;

    private String english ;

    private String pronunciation ;

    private String chinese ;
}
