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
     * 父级
     */
    @TableField("parent_en")
    private String parentEn ;

    /**
     * 级别
     */
    @TableField("level")
    private Integer level ;

    /**
     * 所属词根
     */
    @TableField("belong_root")
    private String belongRoot ;

    /**
     * 父级
     */
    @TableField("common_part")
    private String commonPart ;
}
