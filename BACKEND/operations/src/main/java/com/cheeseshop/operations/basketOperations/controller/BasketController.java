package com.cheeseshop.operations.basketOperations.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeseshop.operations.basketOperations.entity.BasketEntity;
import com.cheeseshop.operations.basketOperations.service.BasketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/operations")
public class BasketController {
    
    @Autowired
    private BasketService basketService;

    @PostMapping("basket")
    public boolean basketControl(@RequestBody BasketEntity data) {
        return basketService.basketControl(data);
    }
    
}
