package com.zpt.demo.service.impl;

import com.zpt.demo.mapper.QuartzTestMapper;
import com.zpt.demo.model.User;
import com.zpt.demo.service.QuartzTestService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartzTestServiceImpl implements QuartzTestService {
    public void getUser() {
        JobDetail jobDetail = JobBuilder.newJob(myJob.class).withIdentity("job1", "group1").build();
        //创建触发器 每10秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        //创建调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();

            //将任务及其触发器放入调度器
            scheduler.scheduleJob(jobDetail, trigger);

            //调度器开始调度任务
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}


class myJob implements Job {

    @Autowired
    private QuartzTestMapper quartzTestMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<User> list = quartzTestMapper.getUser();
        System.out.println(list.get(0));
    }
}
