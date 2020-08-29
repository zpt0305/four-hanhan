package com.zpt.demo.controller;

import com.zpt.demo.service.QuartzTestService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzTestController {

    @Autowired
    private QuartzTestService quartzTestService;

    @RequestMapping("/test")
    public void test() {
        quartzTestService.getUser();
    }
}
