package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.LearnTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ThinkPad on 2018/10/16.
 */
@RestController
@RequestMapping("/api/task")
public class LearnPlanApiController extends AbstractController {

    @LogRecord(name = "getPlanTaskList" ,description = "查询计划任务列表")
    @PostMapping("/getPlanTaskList")
    public Object getPlanTaskList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                    @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                    String taskName ,
                                    String taskState ,
                                    String startTime ,
                                    String endTime){
        Wrapper<LearnTask> wrapper = new EntityWrapper<LearnTask>();
        if (!StringUtils.isEmpty(taskName)) {
            wrapper.where("task_name REGEXP {0}", taskName);
        }
        if (!StringUtils.isEmpty(taskState)) {
            wrapper.where("task_state = {0}", taskState);
        }
        if (!StringUtils.isEmpty(startTime)) {
            wrapper.where("start_time >= {0}", startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            wrapper.where("start_time < date_add({0}, interval 1 day)", endTime);
        }
        Page<LearnTask> pageNew = new Page<LearnTask>(page, size);
        Page selectedPage = learnTaskService.selectPage(pageNew, wrapper);
        return PageData(selectedPage);
    }

    @LogRecord(name = "editPlanTask" ,description = "编辑计划任务信息")
    @PostMapping("/editPlanTask")
    public Object editPlanTask(@RequestBody LearnTask body){
        if(body.getId() == null){
            body.setTaskCode(IdWorker.getIdStr());
        }
        learnTaskService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getPlanTaskInfo" ,description = "根据ID获取计划任务信息")
    @PostMapping("/getPlanTaskInfo")
    public Object getPlanTaskInfo(String id){
        LearnTask planTask = learnTaskService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",planTask));
    }
}
