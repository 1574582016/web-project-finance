package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/5/7.
 */
@Data
@TableName("stock_sector_level")
public class StockSectorLevel extends BaseModel<StockSectorLevel> {

    /**
     *分类
     */
    @TableField("sector_type")
    private String sectorType ;

    /**
     *分类排行
     */
    @TableField("type_order")
    private String typeOrder ;

    /**
     *一级行业
     */
    @TableField("first_sector")
    private String firstSector ;

    /**
     *二级行业
     */
    @TableField("second_sector")
    private String secondSector ;

    /**
     *三级行业
     */
    @TableField("third_sector")
    private String thirdSector ;

    /**
     *四级行业
     */
    @TableField("forth_sector")
    private String forthSector ;

    /**
     *五级行业
     */
    @TableField("five_sector")
    private String fiveSector ;

    /**
     *六级行业
     */
    @TableField("six_sector")
    private String sixSector ;

    /**
     *七级行业
     */
    @TableField("seven_sector")
    private String sevenSector ;

    /**
     *行业关注
     */
    @TableField("sector_focus")
    private String sectorFocus ;

    /**
     *关注度
     */
    @TableField("focus_level")
    private Integer focusLevel;

    /**
     *股票编码
     */
    @TableField("stock_code")
    private String stockCode ;
}
