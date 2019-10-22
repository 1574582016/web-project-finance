package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Data
@TableName("stock_sector_class")
public class StockSectorClass extends BaseModel<StockSectorClass> {

    /**
     *行业编码
     */
    @TableField("sector_code")
    private String sectorCode ;

    /**
     *行业名称
     */
    @TableField("sector_name")
    private String sectorName ;

    /**
     *父级编码
     */
    @TableField("parent_code")
    private String parentCode ;

    /**
     *行业排序
     */
    @TableField("sector_order")
    private Integer sectorOrder ;

    /**
     *企业数量
     */
    @TableField("company_num")
    private Integer companyNum ;
}
