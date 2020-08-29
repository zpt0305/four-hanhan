package com.zpt.demo.others.strategytest;

public enum VIP_LEVEL {
    SENIOR(1,"高级会员"),
    INTERMEDIATE(2,"中级会员"),
    ELEMENTARY(3,"初级会员");

    private int code;
    private String level;

    public int getCode(){
        return this.code;
    }

    VIP_LEVEL(int code,String level){
        this.code = code;
        this.level = level;
    }
}
