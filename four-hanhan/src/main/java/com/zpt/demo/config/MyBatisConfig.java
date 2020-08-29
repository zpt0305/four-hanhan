package com.zpt.demo.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@MapperScan("com.example.testredis.mapper")
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){

            @Override
            public void customize(Configuration configuration) {
                //这里表示开启驼峰命名
                configuration.setMapUnderscoreToCamelCase(true);
                configuration.setCacheEnabled(true);
            }
        };
    }
}