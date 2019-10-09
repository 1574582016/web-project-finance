package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/9.
 */
@Data
@TableName("stock_index_class")
public class StockIndexClass extends BaseModel<StockIndexClass> {

    /**
     *指数编码
     */
    @TableField("index_code")
    private String indexCode ;

    /**
     *指数简称
     */
    @TableField("indx_sname")
    private String indxSname ;

    /**
     *股票数量
     */
    @TableField("stock_num")
    private String stockNum ;

    /**
     *基础点数
     */
    @TableField("base_point")
    private String basePoint ;

    /**
     *基础时间
     */
    @TableField("base_date")
    private String baseDate ;

    /**
     *上线时间
     */
    @TableField("online_date")
    private String onlineDate ;

    /**
     *指数介绍
     */
    @TableField("index_intro")
    private String indexIntro ;

    /**
     *指数全称
     */
    @TableField("index_fullname")
    private String indexFullname ;

    /**
     *系列类型
     */
    @TableField("class_series")
    private String classSeries ;

    /**
     *类型地域
     */
    @TableField("class_region")
    private String classRegion ;

    /**
     *所属资产
     */
    @TableField("class_assets")
    private String classAssets ;

    /**
     *指数类型
     */
    @TableField("class_classify")
    private String classClassify ;

    /**
     *货币指标
     */
    @TableField("class_currency")
    private String classCurrency ;

}
