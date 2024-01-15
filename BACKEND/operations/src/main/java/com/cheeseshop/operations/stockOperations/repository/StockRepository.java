package com.cheeseshop.operations.stockOperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cheeseshop.operations.stockOperations.entity.StockEntity;
import java.util.Optional;


public interface StockRepository extends JpaRepository<StockEntity, Long>{
    
    Optional<StockEntity> findById(Long id);

    
}
