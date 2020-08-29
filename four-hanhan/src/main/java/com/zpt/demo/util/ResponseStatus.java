package com.zpt.demo.util;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

@ToString
@Data
public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = -3435462186394872857L;

    private Integer code;

    private String message;

    private boolean success;

    public ResponseStatus(){

    }

    public ResponseStatus(Integer code){
        this.code = code;
    }

    public ResponseStatus(Integer code,String message,boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
