package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/2.
 */
@Data
public class IndexConstituent_VO extends VoModel {

    private String stockCode ;


    private String stockName ;

    private String stockSector;

    private String indexName;
}
