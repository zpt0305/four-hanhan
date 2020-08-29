package com.zpt.demo.model;

import lombok.Data;

@Data
public class Info {

    public Info(){

    }

    public Info(String name,Integer quesStar,String quesId){
        this.name = name;
        this.quesId = quesId;
        this.quesStar = quesStar;
    }

    private String name;
    private Integer quesStar;
    private String quesId;
}
