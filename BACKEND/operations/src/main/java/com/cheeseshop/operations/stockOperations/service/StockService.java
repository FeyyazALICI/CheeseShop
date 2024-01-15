package com.cheeseshop.operations.stockOperations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheeseshop.operations.stockOperations.entity.StockEntity;
import com.cheeseshop.operations.stockOperations.repository.StockRepository;

@Service
public class StockService {
    

    @Autowired
    private StockRepository stockRepository;
    
    public boolean deleteControl(StockEntity data){
        if(data.getHold_quantity().intValue() == 0){
            stockRepository.delete(data);
            return true;
        }
        return false;
    }
}
