package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/4/29.
 */
@TableName("statistics_day_news")
public class StatisticsDayNews extends BaseModel<StatisticsDayNews> {

    /**
     * 新闻id
     */
    @TableField("news_code")
    private String newsCode ;

    /**
     * 新闻名称
     */
    @TableField("news_name")
    private String newsName ;

    /**
     * 新闻区域
     */
    @TableField("news_region")
    private String newsRegion ;

    /**
     * 新闻类型
     */
    @TableField("news_type")
    private String newsType ;

    /**
     * 新闻内容
     */
    @TableField("news_content")
    private String newsContent ;

    /**
     * 新闻影响
     */
    @TableField("news_influence")
    private String newsInfluence ;

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsRegion() {
        return newsRegion;
    }

    public void setNewsRegion(String newsRegion) {
        this.newsRegion = newsRegion;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsInfluence() {
        return newsInfluence;
    }

    public void setNewsInfluence(String newsInfluence) {
        this.newsInfluence = newsInfluence;
    }
}
