package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/11/28.
 */
@Data
public class StaticSectorNum_VO extends VoModel {

    private String  firstSector;

    private String  secondSector;

    private String  thirdSecotor;

    private String  forthSector;

    private Integer  sectorNum;
}
