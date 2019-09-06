package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/7/8.
 */
@Data
@TableName("forex_index_type")
public class ForexIndexType extends BaseModel<ForexIndexType> {

    @TableField(value = "type_code")
    private String typeCode ;

    @TableField(value = "parent_code")
    private String parentCode ;

    @TableField(value = "type_name")
    private String typeName ;

    @TableField(value = "release_contry")
    private String releaseContry ;

    @TableField(value = "release_dapartment")
    private String releaseDapartment ;

    @TableField(value = "release_frequency")
    private String releaseFrequency ;

    @TableField(value = "index_explain")
    private String indexExplain ;

    @TableField(value = "index_focus_reason")
    private String indexFocusReason ;

    public String getTypeCode() {
        return typeCode;
    }

}
