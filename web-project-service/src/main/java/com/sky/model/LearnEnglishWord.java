package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.MiddleModel;

/**
 * Created by ThinkPad on 2018/10/10.
 */
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

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLearnDate() {
        return learnDate;
    }

    public void setLearnDate(String learnDate) {
        this.learnDate = learnDate;
    }
}
