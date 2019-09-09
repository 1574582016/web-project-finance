package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Data
@TableName("economy_news_statictis")
public class EconomyNewsStatictis extends BaseModel<EconomyNewsStatictis> {

    /**
     * ID
     */
    @TableField("news_code")
    private String newsCode ;

    /**
     * 标题
     */
    @TableField("news_title")
    private String newsTitle ;

    /**
     * 时间
     */
    @TableField("news_time")
    private String newsTime ;

    /**
     * 区域
     */
    @TableField("news_region")
    private Integer newsRegion ;

    /**
     * 国家
     */
    @TableField("news_contry")
    private String newsContry ;

    /**
     * 类型
     */
    @TableField("news_type")
    private Integer newsType ;

    /**
     * 关键词
     */
    @TableField("news_key_word")
    private String newsKeyWord ;

    /**
     * 内容
     */
    @TableField("news_content")
    private String newsContent ;


    @TableField(exist = false)
    List<EconomyNewsInfluence> influencelist ;


}
