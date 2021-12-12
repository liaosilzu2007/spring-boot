package com.lzumetal.springboot.delaytask.controller;

import com.lzumetal.springboot.delaytask.service.DelayTaskService;
import com.lzumetal.springboot.utils.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2021-11-21
 */
@RestController
public class TaskController {


    @Autowired
    private DelayTaskService delayTaskService;


    @RequestMapping("/add")
    private ResponseData add(@RequestParam String taskType, @RequestParam String bizId) {
        delayTaskService.commit(taskType, bizId);
        return ResponseData.success();
    }

}
