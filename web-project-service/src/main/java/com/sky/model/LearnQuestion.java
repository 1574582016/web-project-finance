package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@TableName("learn_question")
public class LearnQuestion extends BaseModel<LearnQuestion> {

    @TableField("question_code")
    private String questionCode;

    @TableField("question_content")
    private String questionContent;

    @TableField("question_type")
    private String questionType;

    @TableField("question_answer")
    private String questionAnswer;

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
