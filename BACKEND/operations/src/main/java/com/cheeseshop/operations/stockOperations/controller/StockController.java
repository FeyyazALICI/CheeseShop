package com.cheeseshop.operations.stockOperations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeseshop.operations.stockOperations.entity.StockEntity;
import com.cheeseshop.operations.stockOperations.service.StockService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/operations/stock")
public class StockController {
    

    @Autowired
    private StockService stockService;

    @PostMapping
    public boolean deleteControl(@RequestBody StockEntity data){
        return stockService.deleteControl(data);
    }
}
