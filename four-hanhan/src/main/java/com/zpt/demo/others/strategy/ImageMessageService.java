package com.zpt.demo.others.strategy;

import org.springframework.stereotype.Service;

@Service
@MsgTypeHandler(value = MSG_TYPE.IMAGE)
public class ImageMessageService implements MessageService {
    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理图片消息" + messageInfo.getContent());
    }
}
