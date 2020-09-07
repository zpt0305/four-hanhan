package com.zpt.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";
    public static final String EXCHANGE_FANOUT = "fanout_Exchange";
    public static final String EXCHANGE_TOPIC = "topic_Exchange";
    public static final String EXCHANGE_HEADERS = "headers_Exchange";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";
    public static final String QUEUE_D = "QUEUE_D";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";
    public static final String ROUTINGKEY_TOPIC_A = "topicA.#";
    public static final String ROUTINGKEY_TOPIC_B = "topicB.#";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        //connectionFactory.setVirtualHost("zpt");
        //connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */



    //直连型交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    //Fanout交换机，广播形式
    @Bean
    public FanoutExchange fanoutExchange(){ return new FanoutExchange(EXCHANGE_FANOUT); }

    //topic交换机
    @Bean
    public TopicExchange topicExchange(){
         return new TopicExchange(EXCHANGE_TOPIC);
    }

    //headers交换机
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(EXCHANGE_HEADERS);
    }

    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA(){
        return new Queue(QUEUE_A,true);
    }

    @Bean
    public Queue queueB(){
        return new Queue(QUEUE_B,true);
    }

    @Bean
    public Binding directExchangeBindingA() {
        return BindingBuilder.bind(queueA()).to(directExchange()).with(RabbitMQConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding directExchangeBindingB(){
        return BindingBuilder.bind(queueB()).to(directExchange()).with(RabbitMQConfig.ROUTINGKEY_B);
    }


    @Bean
    public Binding fanoutExchangeBindingA(){
        return  BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutExchangeBindingB(){
        return  BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    public Binding topicExchangeBindingA(){
        return BindingBuilder.bind(queueA()).to(topicExchange()).with(ROUTINGKEY_TOPIC_A);
    }

    @Bean
    public Binding topicExchangeBindingB(){
        return BindingBuilder.bind(queueB()).to(topicExchange()).with(ROUTINGKEY_TOPIC_B);
    }

    @Bean
    public Binding headersExchangeBindingA(){
        Map<String,Object> header = new HashMap<>();
        header.put("queue", "queueA");
        header.put("bindType", "whereAll");
        return BindingBuilder.bind(queueA()).to(headersExchange()).whereAll(header).match();
    }

    @Bean
    public Binding headersExchangeBindingB(){
        Map<String,Object> header = new HashMap<>();
        header.put("queue", "queueB");
        header.put("bindType", "whereAny");
        return BindingBuilder.bind(queueB()).to(headersExchange()).whereAny(header).match();
    }
}