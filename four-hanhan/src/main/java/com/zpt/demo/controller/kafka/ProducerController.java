/*
package com.zpt.demo.controller.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @author toutou
 * @date by 2019/08
 *//*

@RestController
@RequestMapping("/message")
public class ProducerController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("/send")
    public String send(String msg){
        kafkaTemplate.send("zpt", msg); //使用kafka模板发送信息
        return "success";
    }

    @RequestMapping("/aa")
    public String aaa(String msg){
        System.out.println("lalalalalalalalalalal");
        return "lalalalalalalalalalal";
    }
}*/
