package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/2/14.
 */
@Data
@TableName("learn_english_class")
public class LearnEnglishClass extends MiddleModel<LearnEnglishClass> {

    /**
     * 单词相同部分片段
     */
    @TableField("fragment")
    private String fragment ;

    /**
     * 英语
     */
    @TableField("english")
    private String english ;

    /**
     * 音标
     */
    @TableField("pronunciation")
    private String pronunciation ;

    /**
     * 中文
     */
    @TableField("chinese")
    private String chinese ;

    /**
     * 数量
     */
    @TableField("size")
    private Integer size ;

}
