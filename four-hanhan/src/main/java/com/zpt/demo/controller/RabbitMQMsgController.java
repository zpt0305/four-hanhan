package com.zpt.demo.controller;

import com.zpt.demo.others.producer.RabbitMQMsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQMsgController {

    @Autowired
    RabbitMQMsgProducer msgProducer;

    @RequestMapping("/simpleMsgSend")
    public void simpleMsgSend(){
        msgProducer.simpleMsgSend("你好 简单消息模型！");
    }

    @RequestMapping("/directExchangeMsgSend")
    public void directExchangeMsgSend(){
        msgProducer.directExchangeMsgSend("你好 直连式交换机！");
    }

    @RequestMapping("/fanoutExchangeMsgSend")
    public void fanoutExchangeMsgSend(){
        msgProducer.fanoutExchangeMsgSend("你好 广播式交换机！");
    }

    @RequestMapping("/topicMsgSend")
    public void topicMsgSend(){
        msgProducer.topicMsgSend("你好 主题式交换机！");
    }

    @RequestMapping("/headersMsgSend")
    public void headersMsgSend(){
        msgProducer.headersMsgSend("你好 头式交换机！");
    }

}
