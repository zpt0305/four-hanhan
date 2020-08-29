package com.zpt.demo.controller;

import com.zpt.demo.others.strategy.MSG_TYPE;
import com.zpt.demo.others.strategy.MessageInfo;
import com.zpt.demo.others.strategy.MessageService;
import com.zpt.demo.others.strategy.MessageServiceContext;
import com.zpt.demo.others.strategytest.VIP_LEVEL;
import com.zpt.demo.others.strategytest.VipInfo;
import com.zpt.demo.others.strategytest.VipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    MessageServiceContext messageServiceContext;

    @RequestMapping("/test")
    public void contextLoads() {
        // 构建一个文本消息
        MessageInfo messageInfo = new MessageInfo(MSG_TYPE.TEXT.code, "哈哈哈哈");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getType());
        // 处理文本消息 消息内容
        // 可以看到文本消息被文本处理类所处理
        messageService.handleMessage(messageInfo);
    }

    @Autowired
    VipLevelService Senior;

    @RequestMapping("/vipLevel")
    public void vipLevel() {
        VipInfo vipInfo = new VipInfo(VIP_LEVEL.SENIOR.getCode());
        Senior.getDiscount(vipInfo.getLevel());
        // 构建一个文本消息
        MessageInfo messageInfo = new MessageInfo(MSG_TYPE.TEXT.code, "哈哈哈哈");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getType());
        // 处理文本消息 消息内容
        // 可以看到文本消息被文本处理类所处理
        messageService.handleMessage(messageInfo);
    }

}
