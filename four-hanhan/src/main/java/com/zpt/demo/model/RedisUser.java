package com.zpt.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisUser implements Serializable {
    private int id;
    private String userName;
    private String password;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
