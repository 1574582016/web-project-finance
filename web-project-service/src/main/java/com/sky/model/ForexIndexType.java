package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/7/8.
 */
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

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getReleaseContry() {
        return releaseContry;
    }

    public void setReleaseContry(String releaseContry) {
        this.releaseContry = releaseContry;
    }

    public String getReleaseDapartment() {
        return releaseDapartment;
    }

    public void setReleaseDapartment(String releaseDapartment) {
        this.releaseDapartment = releaseDapartment;
    }

    public String getReleaseFrequency() {
        return releaseFrequency;
    }

    public void setReleaseFrequency(String releaseFrequency) {
        this.releaseFrequency = releaseFrequency;
    }

    public String getIndexExplain() {
        return indexExplain;
    }

    public void setIndexExplain(String indexExplain) {
        this.indexExplain = indexExplain;
    }

    public String getIndexFocusReason() {
        return indexFocusReason;
    }

    public void setIndexFocusReason(String indexFocusReason) {
        this.indexFocusReason = indexFocusReason;
    }
}
