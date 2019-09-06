package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/16.
 */
@Data
@TableName("learn_task")
public class LearnTask extends BaseModel<LearnTask> {

    @TableField("task_code")
    private String taskCode ;

    @TableField("task_name")
    private String taskName ;

    @TableField("start_time")
    private String startTime ;

    @TableField("task_state")
    private String taskState ;

    @TableField("task_content")
    private String taskContent ;

}
