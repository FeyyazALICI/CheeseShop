package com.customer.customer.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    
    private String response;

    public ApiResponse(String response){
        this.response = response;
    }
}
