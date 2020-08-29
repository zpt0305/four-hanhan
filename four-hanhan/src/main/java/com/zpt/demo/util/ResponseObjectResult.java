package com.zpt.demo.util;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class ResponseObjectResult {

    private Map<String,Object> data;

    private ResponseStatus responseStatus;

    public ResponseObjectResult(){

    }
    public ResponseObjectResult(ResponseStatus responseStatus){
        this.responseStatus = responseStatus;
    }
    public ResponseObjectResult(ResponseStatus responseStatus, Map<String,Object> data){
        this.responseStatus = responseStatus;
        this.data = data;
    }
}
