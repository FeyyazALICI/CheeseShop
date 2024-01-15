package com.cheeseshop.operations.basketOperations.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "basket")
public class BasketEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long basket_id;

    private long customer_id;

    private long stock_id;

    private BigDecimal quantity;

    private String transaction_status;

    public BasketEntity(){
        // Default no-arg constructor
    }

    public BasketEntity(long basket_id, long customer_id, long stock_id, BigDecimal quantity, String status){
        this.basket_id = basket_id;
        this.customer_id = customer_id;
        this.stock_id = stock_id;
        this.quantity = quantity;
        this.transaction_status = status;
    }
}

