package com.habeeb.ecommerce.request;

import lombok.Data;

@Data
public class CreateUserReq {
    private String name;
    private String email;
    private String password; 
}
