package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;

/**
 * Created by ThinkPad on 2019/2/14.
 */
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

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
