package com.zpt.demo.others.strategytest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class VipInfo {
    private int level;
    private String discount;

    public VipInfo(int level){
        this.level = level;
    }

}
