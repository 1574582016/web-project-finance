package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/9/30.
 */
@Data
@TableName("forex_news_statictis")
public class ForexNewsStatictis extends BaseModel<ForexNewsStatictis> {

    /**
     * 时间
     */
    @TableField("news_time")
    private String newsTime ;

    /**
     * 标题
     */
    @TableField("news_title")
    private String newsTitle ;

    /**
     * 级别
     */
    @TableField("news_level")
    private String newsLevel ;

    /**
     * 主题
     */
    @TableField("news_topic")
    private String newsTopic ;

    /**
     * 热点
     */
    @TableField("news_hot")
    private String newsHot ;

    /**
     * 关键字
     */
    @TableField("key_word")
    private String keyWord ;

    /**
     * 类型
     */
    @TableField("news_type")
    private String newsType ;

    /**
     * 内容
     */
    @TableField("news_content")
    private String newsContent ;

    /**
     * 关键词
     */
    @TableField("news_url")
    private String newsUrl ;
}
