package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/9/5.
 */
@Data
@TableName("stock_company_notice_class")
public class StockCompanyNoticeClass extends BaseModel<StockCompanyNoticeClass> {

    /**
     *类型编码
     */
    @TableField("class_code")
    private String classCode ;

    /**
     *类型名称
     */
    @TableField("class_name")
    private String className ;

    /**
     *父级编码
     */
    @TableField("parent_code")
    private String parentCode ;

    /**
     *排序
     */
    @TableField("order_num")
    private Integer orderNum ;

    /**
     *重要级别
     */
    @TableField("class_level")
    private Integer classLevel ;

    /**
     *描述
     */
    @TableField("class_desc")
    private String classDesc ;

}
