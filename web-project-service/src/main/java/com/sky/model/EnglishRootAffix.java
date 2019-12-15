package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;
import lombok.Data;

/**
 * Created by Administrator on 2019/12/15/015.
 */
@Data
@TableName("english_root_affix")
public class EnglishRootAffix extends MiddleModel<EnglishRootAffix> {

    @TableField("same_root")
    private String sameRoot ;

    @TableField("root_affix")
    private String rootAffix ;

    @TableField("root_type")
    private String rootType ;

    @TableField("root_mean")
    private String rootMean ;

    @TableField("root_resource")
    private String rootResource ;
}
