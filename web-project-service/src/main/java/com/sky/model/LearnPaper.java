package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@TableName("learn_paper")
public class LearnPaper extends BaseModel<LearnPaper> {

    @TableField("pager_code")
    private String pagerCode;

    @TableField("pager_name")
    private String pagerName;

    @TableField("pager_type")
    private String pagerType;

    @TableField("pager_time")
    private String pagerTime;

    @TableField("pager_annotate")
    private String pagerAnnotate;

    public String getPagerCode() {
        return pagerCode;
    }

    public void setPagerCode(String pagerCode) {
        this.pagerCode = pagerCode;
    }

    public String getPagerName() {
        return pagerName;
    }

    public void setPagerName(String pagerName) {
        this.pagerName = pagerName;
    }

    public String getPagerType() {
        return pagerType;
    }

    public void setPagerType(String pagerType) {
        this.pagerType = pagerType;
    }

    public String getPagerTime() {
        return pagerTime;
    }

    public void setPagerTime(String pagerTime) {
        this.pagerTime = pagerTime;
    }

    public String getPagerAnnotate() {
        return pagerAnnotate;
    }

    public void setPagerAnnotate(String pagerAnnotate) {
        this.pagerAnnotate = pagerAnnotate;
    }
}
