package com.zpt.demo.others.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageServiceContext {
    private final Map<Integer, MessageService> handlerMap = new HashMap<>();

    public MessageService getMessageService(Integer type){
        return handlerMap.get(type);
    }

    public void putMessageService(Integer code, MessageService messageService){
        handlerMap.put(code, messageService);
    }
}
