package com.customer.customer.customer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.customer.customer.entity.BasketEntity;
import com.customer.customer.customer.entity.CustomerEntity;
import com.customer.customer.customer.service.CustomerService;
import com.customer.customer.response.ApiResponse;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

// auxilary imports
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<CustomerEntity> getAllData() {
        return customerService.getAllData();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> register(@RequestBody CustomerEntity data) {
        return customerService.register(data);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@RequestBody CustomerEntity data) {
        return customerService.delete(data);
    }

    //Basket
    @PostMapping("/basket")
    public ResponseEntity<ApiResponse> basket(@RequestBody BasketEntity data){
        return customerService.basket(data);
    }
    
}
