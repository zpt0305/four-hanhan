package com.zpt.demo.controller;

import com.zpt.demo.mapper.QuartzTestMapper;
import com.zpt.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
//@EnableScheduling
@RequestMapping("/schedule")
public class ScheduleTest {

    @Autowired
    private QuartzTestMapper quartzTestMapper;

    @RequestMapping("/test")
    //@Scheduled(cron = "0/5 * * * * ?")
    //@Scheduled(fixedRate = 5000)
    public void test(){
        List<User> list = quartzTestMapper.getUser();
        System.out.println("当前时间：" + LocalDateTime.now());
        System.out.println(list.get(0));
    }
}
