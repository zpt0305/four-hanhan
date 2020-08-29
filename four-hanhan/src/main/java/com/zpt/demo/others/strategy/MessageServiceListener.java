package com.zpt.demo.others.strategy;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageServiceListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(MsgTypeHandler.class);
        MessageServiceContext messageServiceContext = event.getApplicationContext().getBean(MessageServiceContext.class);
        beans.forEach((name, bean)->{
            MsgTypeHandler typeHandler = bean.getClass().getAnnotation(MsgTypeHandler.class);
            messageServiceContext.putMessageService(typeHandler.value().code,(MessageService) bean);
        });
    }
}
