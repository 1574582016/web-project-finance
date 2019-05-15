package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@TableName("economy_news_influence")
public class EconomyNewsInfluence extends BaseModel<EconomyNewsInfluence> {

    /**
     * 新闻ID
     */
    @TableField("news_code")
    private String newsCode ;

    /**
     * ID
     */
    @TableField("influence_code")
    private String influenceCode ;

    /**
     * 影响时长
     */
    @TableField("influence_time")
    private String influenceTime ;

    /**
     * 影响程度1-严重影响;2-比较影响;3-一般影响;4-较弱影响;5-无影响',
     */
    @TableField("influence_degree")
    private String influenceDegree ;

    /**
     * 影响范围
     */
    @TableField("influence_scope")
    private String influenceScope ;

    /**
     * 影响内容
     */
    @TableField("influence_content")
    private String influenceContent ;

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getInfluenceCode() {
        return influenceCode;
    }

    public void setInfluenceCode(String influenceCode) {
        this.influenceCode = influenceCode;
    }

    public String getInfluenceTime() {
        return influenceTime;
    }

    public void setInfluenceTime(String influenceTime) {
        this.influenceTime = influenceTime;
    }

    public String getInfluenceDegree() {
        return influenceDegree;
    }

    public void setInfluenceDegree(String influenceDegree) {
        this.influenceDegree = influenceDegree;
    }

    public String getInfluenceScope() {
        return influenceScope;
    }

    public void setInfluenceScope(String influenceScope) {
        this.influenceScope = influenceScope;
    }

    public String getInfluenceContent() {
        return influenceContent;
    }

    public void setInfluenceContent(String influenceContent) {
        this.influenceContent = influenceContent;
    }
}
