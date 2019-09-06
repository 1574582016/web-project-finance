package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/13.
 */
@Data
@TableName("learn_diary")
public class LearnDiary extends BaseModel<LearnDiary>{

    /**
     * 日记id
     */
    @TableField("diary_code")
    private String diaryCode ;

    /**
     * 名称
     */
    @TableField("name")
    private String name ;

    /**
     * 类型
     */
    @TableField("type")
    private String type ;

    /**
     * 摘要
     */
    @TableField("summary")
    private String summary ;

    @TableField("files_path")
    private String filesPath ;

    /**
     * 内容
     */
    @TableField(exist = false)
    private String content ;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark ;


}
