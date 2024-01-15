package com.customer.customer.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.customer.customer.customer.entity.BasketEntity;


public interface BasketRepository extends JpaRepository<BasketEntity, Long>{
    

}
