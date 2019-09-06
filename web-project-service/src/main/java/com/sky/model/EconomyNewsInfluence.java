package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Data
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

}
