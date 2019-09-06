package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Data
@TableName("learn_paper")
public class LearnPaper extends BaseModel<LearnPaper> {

    @TableField("pager_code")
    private String pagerCode;

    @TableField("pager_name")
    private String pagerName;

    @TableField("pager_type")
    private String pagerType;

    @TableField("pager_time")
    private String pagerTime;

    @TableField("pager_annotate")
    private String pagerAnnotate;

}
