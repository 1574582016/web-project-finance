package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Data
@TableName("stock_company_analyse")
public class StockCompanyAnalyse extends BaseModel<StockCompanyAnalyse> {

    /**
     * 股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 经营要点
     */
    @TableField("operate_point")
    private String operatePoint ;

    /**
     * 要点分析
     */
    @TableField("point_analyse")
    private String pointAnalyse ;

}
