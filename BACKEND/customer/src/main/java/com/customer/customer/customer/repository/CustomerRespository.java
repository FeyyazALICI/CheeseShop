package com.customer.customer.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.customer.customer.customer.entity.CustomerEntity;



public interface CustomerRespository extends JpaRepository<CustomerEntity, Long>{
    

    boolean existsByEmail(String email);
    CustomerEntity findByEmail(String email);

}
