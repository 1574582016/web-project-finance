package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/2.
 */
@Data
@TableName("stock_index_constituent")
public class StockIndexConstituent extends BaseModel<StockIndexConstituent> {

    /**
     *
     */
    @TableField("publish_time")
    private String publishTime ;

    /**
     *
     */
    @TableField("index_type")
    private String indexType ;

    /**
     *
     */
    @TableField("index_code")
    private String indexCode ;

    /**
     *
     */
    @TableField("index_name")
    private String indexName ;

    /**
     *
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     *
     */
    @TableField("stock_weight")
    private BigDecimal stockWeight ;

    /**
     *
     */
    @TableField("chose_num")
    private Integer choseNum ;
}
