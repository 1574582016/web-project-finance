package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@TableName("learn_pager_question")
public class LearnPagerQuestion extends MiddleModel<LearnPagerQuestion> {

    @TableField("pager_code")
    private String pagerCode;

    @TableField("question_code")
    private String questionCode;

    public String getPagerCode() {
        return pagerCode;
    }

    public void setPagerCode(String pagerCode) {
        this.pagerCode = pagerCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }
}
