package com.zpt.demo.others.strategytest;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface LevelHandler {
    VIP_LEVEL value();
}
