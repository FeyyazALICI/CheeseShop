package com.supplier.supplier.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.supplier.supplier.supplier.entity.StockEntity;



public interface SupplierRepository extends JpaRepository<StockEntity, Long>{
    
    StockEntity findByid(Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StockEntity s WHERE s.group_id = :groupId")
    boolean existsByGroupId(@Param("groupId") String groupId);

    boolean existsById(Long id);
}
