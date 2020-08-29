package com.zpt.demo.others.strategytest;

import org.springframework.stereotype.Service;

@Service
@LevelHandler(value = VIP_LEVEL.INTERMEDIATE)
public class Intermediate implements VipLevelService {
    /*@Override
    public String getDiscount() {
        return "8折";
    }*/

    @Override
    public String getDiscount(int level) {
        return null;
    }
}
