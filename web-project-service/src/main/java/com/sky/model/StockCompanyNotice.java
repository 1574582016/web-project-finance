package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/9/4.
 */
@Data
@TableName("stock_company_notice")
public class StockCompanyNotice extends BaseModel<StockCompanyNotice> {

    /**
     *股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *股票名称
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     *公告时间
     */
    @TableField("publish_time")
    private String publishTime ;

    /**
     *分类编码
     */
    @TableField("class_code")
    private String classCode ;

    /**
     *大分类
     */
    @TableField("big_notice_class")
    private String bigNoticeClass ;

    /**
     *中分类
     */
    @TableField("middle_notice_class")
    private String middleNoticeClass ;

    /**
     *公告名称
     */
    @TableField("notice_title")
    private String noticeTitle ;

    /**
     *公告类型
     */
    @TableField("notice_type")
    private String noticeType ;

    /**
     *公告内容
     */
    @TableField("notice_contain")
    private String noticeContain ;

    /**
     *公告详情
     */
    @TableField("notice_detail")
    private String noticeDetail ;

}
