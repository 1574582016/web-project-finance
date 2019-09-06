package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/10.
 */
@Data
@TableName("learn_english_word")
public class LearnEnglishWord extends MiddleModel<LearnEnglishWord> {

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
     * 例句
     */
    @TableField("sentence")
    private String sentence ;

    /**
     * 级别
     */
    @TableField("level")
    private String level ;

    /**
     * 是否掌握
     */
    @TableField("master")
    private String master ;

    /**
     * 分类
     */
    @TableField("type")
    private String type ;

    /**
     * 学习时间
     */
    @TableField("learn_date")
    private String learnDate ;

}
