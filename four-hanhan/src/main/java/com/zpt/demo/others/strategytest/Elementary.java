package com.zpt.demo.others.strategytest;

import org.springframework.stereotype.Service;

@LevelHandler(value = VIP_LEVEL.ELEMENTARY)
@Service
public class Elementary implements VipLevelService {
    /*@Override
    public String getDiscount() {
        return "9折";
    }*/

    @Override
    public String getDiscount(int level) {
        return null;
    }
}
