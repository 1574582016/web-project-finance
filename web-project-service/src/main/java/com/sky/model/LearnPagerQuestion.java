package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Data
@TableName("learn_pager_question")
public class LearnPagerQuestion extends MiddleModel<LearnPagerQuestion> {

    @TableField("pager_code")
    private String pagerCode;

    @TableField("question_code")
    private String questionCode;

}
