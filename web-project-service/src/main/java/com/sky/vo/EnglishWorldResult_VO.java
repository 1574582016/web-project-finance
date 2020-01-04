package com.sky.vo;

import com.alibaba.fastjson.JSONObject;
import com.sky.core.model.VoModel;
import com.sky.model.LearnEnglishWord;
import lombok.Data;

import java.util.List;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Data
public class EnglishWorldResult_VO extends VoModel {

    private List<JSONObject> noneList ;

    private List<JSONObject> verbList ;

    private List<JSONObject> adjList ;

    private List<JSONObject> advList ;

}
