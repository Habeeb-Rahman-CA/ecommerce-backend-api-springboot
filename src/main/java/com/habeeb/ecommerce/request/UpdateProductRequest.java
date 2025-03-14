package com.habeeb.ecommerce.request;

import java.math.BigDecimal;

import com.habeeb.ecommerce.model.Category;

import lombok.Data;

@Data
public class UpdateProductRequest {

    // Request Interface
    private long id;
    private String name;
    private String brand;
    private String desc;
    private BigDecimal price;
    private int quantity;
    private int inventory;
    private Category category;
}
