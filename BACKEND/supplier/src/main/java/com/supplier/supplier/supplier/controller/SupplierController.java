package com.supplier.supplier.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.supplier.response.ApiResponse;
import com.supplier.supplier.supplier.entity.StockEntity;
import com.supplier.supplier.supplier.service.SupplierService;
import com.supplier.supplier.supplier.service.DTO.SupplierDTO;

import org.springframework.web.bind.annotation.RequestBody;

// auxilary imports
import java.util.List;

@RestController
@RequestMapping("/stock")
public class SupplierController {
    

    @Autowired
    private SupplierService supplierService;


    @GetMapping
    public List<StockEntity> getAllData(){
        return supplierService.getAllData();
    }

    @PutMapping
    public ResponseEntity<ApiResponse> changeStock(@RequestBody SupplierDTO data){
        return supplierService.changeStock(data);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody StockEntity data){
        return supplierService.addProduct(data);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProduct(@RequestBody StockEntity data){
        System.out.println("called");
        return supplierService.deleteProduct(data);
    }

}
