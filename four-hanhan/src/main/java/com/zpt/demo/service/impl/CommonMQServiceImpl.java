package com.zpt.demo.service.impl;

import com.zpt.demo.config.RabbitMQConfig;
import com.zpt.demo.service.CommonMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonMQServiceImpl implements CommonMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendRobbingMsg(String phone) {
        try {
            rabbitTemplate.setExchange(RabbitMQConfig.EXCHANGE_TOPIC_ORDER);
            rabbitTemplate.setRoutingKey(RabbitMQConfig.ROUTINGKEY_A);
            Message message = MessageBuilder.withBody(phone.getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            rabbitTemplate.send(message);
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
