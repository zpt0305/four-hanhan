package com.zpt.demo.others.consumer;

import com.zpt.demo.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class RabbitMQMsgReceiver1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitMQConfig.QUEUE_A)
    @RabbitHandler
    public void process(String content){
        logger.info("接收处理队列A当中的消息： " + content);
    }

}