package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Data
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

}
