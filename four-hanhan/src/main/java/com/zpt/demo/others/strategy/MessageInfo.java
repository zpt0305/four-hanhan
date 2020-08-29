package com.zpt.demo.others.strategy;

import lombok.Data;

@Data
public class MessageInfo {

    private Integer type;

    private  String content;

    public MessageInfo(Integer type, String content){
        this.type = type;
        this.content = content;
    }

}
