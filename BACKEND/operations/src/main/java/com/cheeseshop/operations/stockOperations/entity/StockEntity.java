package com.cheeseshop.operations.stockOperations.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "stock")
@Data
@Entity
public class StockEntity {
    
    /**
    * Last Update on 31.12.2023
    *
    * @author Feyyaz ALICI
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // id should not be BigDecimal for auto_increment values
    private String group_id;
    private BigDecimal hold_quantity;
    private BigDecimal active_quantity;

    public StockEntity(){
        // Default empty constructor
    }

    public StockEntity(Long id,  String group_id, BigDecimal hold_quantity, BigDecimal active_quantity){
        this.id=id;
        this.group_id=group_id;
        this.hold_quantity=hold_quantity;
        this.active_quantity=active_quantity;
    }
}
