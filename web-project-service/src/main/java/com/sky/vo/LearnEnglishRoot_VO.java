package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2020/1/4/004.
 */
@Data
public class LearnEnglishRoot_VO extends VoModel {

    private String root;

    private Integer colRow ;

    private List<EnglishWorldResult_VO> list;
}
