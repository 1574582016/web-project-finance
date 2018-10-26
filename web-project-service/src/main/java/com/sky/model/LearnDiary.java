package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2018/10/13.
 */
@TableName("learn_diary")
public class LearnDiary extends BaseModel<LearnDiary>{

    /**
     * 日记id
     */
    @TableField("diary_code")
    private String diaryCode ;

    /**
     * 名称
     */
    @TableField("name")
    private String name ;

    /**
     * 类型
     */
    @TableField("type")
    private String type ;

    /**
     * 摘要
     */
    @TableField("summary")
    private String summary ;

    @TableField("files_path")
    private String filesPath ;

    /**
     * 内容
     */
    @TableField(exist = false)
    private String content ;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark ;


    public String getDiaryCode() {
        return diaryCode;
    }

    public void setDiaryCode(String diaryCode) {
        this.diaryCode = diaryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }
}
