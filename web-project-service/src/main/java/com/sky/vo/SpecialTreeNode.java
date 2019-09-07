package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/7.
 */
@Data
public class SpecialTreeNode extends VoModel {

    private String name ;


    private List<SpecialTreeNode> children ;
}
