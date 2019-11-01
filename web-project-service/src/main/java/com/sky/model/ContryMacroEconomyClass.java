package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/11/1.
 */
@Data
@TableName("contry_macro_economy_class")
public class ContryMacroEconomyClass extends BaseModel<ContryMacroEconomyClass> {

    /**
     *类型编码
     */
    @TableField("class_code")
    private String classCode ;

    /**
     *国家类别
     */
    @TableField("contry_class")
    private String contryClass ;

    /**
     *类型级别
     */
    @TableField("class_level")
    private Integer classLevel;

    /**
     *数据种类
     */
    @TableField("index_class")
    private String indexClass ;

    /**
     *次级数据分类
     */
    @TableField("sub_index_class")
    private String subIndexClass ;

}
