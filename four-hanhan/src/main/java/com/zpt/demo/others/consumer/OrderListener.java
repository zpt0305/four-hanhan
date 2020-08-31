package com.zpt.demo.others.consumer;

import com.rabbitmq.client.Channel;
import com.zpt.demo.service.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareBatchMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("orderListener")
public class OrderListener implements ChannelAwareBatchMessageListener {

    @Autowired
    private ConcurrencyService concurrencyService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            String phone = new String(body,"UTF-8");
            log.info("监听到的抢单手机号：{}",phone);
            concurrencyService.manageRobbing(phone);
            channel.basicAck(tag,true);
        }catch (Exception e){
            log.error("用户商城抢单发生异常：", e.fillInStackTrace());
            channel.basicAck(tag,false);
        }
    }

    @Override
    public void onMessageBatch(List<Message> messages, Channel channel) {

    }
}
