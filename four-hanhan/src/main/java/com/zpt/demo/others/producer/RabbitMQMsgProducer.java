package com.zpt.demo.others.producer;

import com.zpt.demo.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQMsgProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public RabbitMQMsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }

    public void simpleMsgSend(String content) {
        //指定消费队列A
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_A,content);
    }

    public void directExchangeMsgSend(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //指定消费队列A
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_A,RabbitMQConfig.ROUTINGKEY_A,content,correlationId);
    }

    public void fanoutExchangeMsgSend(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //指定消费队列A
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_FANOUT,"",content,correlationId);
    }

    public void topicMsgSend(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //根据routingKey路由键，匹配到的是key = “topic.A.#”的队列A
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPIC,"topicB.lalalala",content,correlationId);
    }

    public void headersMsgSend(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("queue", "queueB");
        //messageProperties.setHeader("bindType", "whereAny");
        messageProperties.setContentType("text/plain");
        Message message = new Message(content.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_HEADERS,null,message,correlationId);
    }
}