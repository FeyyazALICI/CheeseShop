package com.cheeseshop.operations.basketOperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cheeseshop.operations.basketOperations.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Long>{
    

}
