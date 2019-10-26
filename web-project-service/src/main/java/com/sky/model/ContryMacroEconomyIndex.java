package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/26.
 */
@Data
@TableName("contry_macro_economy_index")
public class ContryMacroEconomyIndex extends BaseModel<ContryMacroEconomyIndex> {

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
     *公布日期
     */
    @TableField("publish_day")
    private String publishDay ;

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

    /**
     *公布值
     */
    @TableField("publish_value")
    private String publishValue ;
}
