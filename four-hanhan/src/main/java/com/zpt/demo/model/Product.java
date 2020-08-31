package com.zpt.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements Serializable {

    private Integer productNo;
    private String productName;
    private BigDecimal price;
    private String description;
    private String unit;
    private int count;

}
