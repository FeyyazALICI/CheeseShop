package com.cheeseshop.operations.basketOperations.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheeseshop.operations.basketOperations.entity.BasketEntity;
import com.cheeseshop.operations.basketOperations.repository.BasketRepository;
import com.cheeseshop.operations.stockOperations.entity.StockEntity;
import com.cheeseshop.operations.stockOperations.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class BasketService {
    

    @Autowired BasketRepository basketRepository;
    @Autowired StockRepository stockRepository;

    @Transactional
    public boolean basketControl(BasketEntity data){
        Long stockId = data.getStock_id();
        StockEntity stockItem = stockRepository.findById(stockId).orElse(null);
        if(stockItem != null){
            BigDecimal stockQuantity = stockItem.getActive_quantity();
            BigDecimal basketQuantity = data.getQuantity();
            int compareStockandQuantity = stockQuantity.compareTo(basketQuantity);
            if(compareStockandQuantity >=0){
                // Revising active quantity
                BigDecimal revisedActive=stockQuantity.subtract(basketQuantity);
                stockItem.setActive_quantity(revisedActive);
                
                // Revising hold quantity
                BigDecimal revisedHold = stockItem.getHold_quantity().add(basketQuantity);
                stockItem.setHold_quantity(revisedHold);

                // Updating stockItem
                stockRepository.saveAndFlush(stockItem);
                // Inserting into basket repository
                basketRepository.saveAndFlush(data);
                return true;
            }
            return false;
        }else{
            return false;
        }
    }
}
