package com.supplier.supplier.supplier.service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.supplier.supplier.response.ApiResponse;
import com.supplier.supplier.supplier.entity.StockEntity;
import com.supplier.supplier.supplier.repository.SupplierRepository;
import com.supplier.supplier.supplier.service.DTO.SupplierDTO;

import jakarta.transaction.Transactional;


import java.math.BigDecimal;
// auxilary imports
import java.util.List;

@Repository
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // GET ALL STOCK DATA
    //---------------------------------------------------------------------------------------------------
    public List<StockEntity> getAllData(){
        return supplierRepository.findAll();
    }
    //---------------------------------------------------------------------------------------------------


    
    // CHANGE STOCK OF A PARTICULAR PRODUCT
    //---------------------------------------------------------------------------------------------------
    @Transactional
    public ResponseEntity<ApiResponse> changeStock(SupplierDTO data){
        Long id = data.getId();
        BigDecimal amount = data.getAmount();
        String sign = data.getSign();
        boolean stockCheck = supplierRepository.existsById(id);
        if(stockCheck){
            try{
                StockEntity stockToChange = supplierRepository.findByid(id);
                if(sign.equals("+")){
                    BigDecimal newActiveStockQuantity = stockToChange.getActive_quantity().add(amount);
                    stockToChange.setActive_quantity(newActiveStockQuantity);
                    supplierRepository.saveAndFlush(stockToChange);
                    return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
                }else{
                    BigDecimal aboveZero = stockToChange.getActive_quantity().subtract(amount);
                    if(   aboveZero.compareTo(new BigDecimal(0)) >=0   ){
                        BigDecimal newActiveStockQuantity = aboveZero;
                        stockToChange.setActive_quantity(newActiveStockQuantity);
                        supplierRepository.saveAndFlush(stockToChange);
                        return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
                    }
                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("The active stock amount can not go below 0!"));
                }
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Service Error!"));
            }
            
        }else{
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("The item doesn't exist!"));
        }
    }
    //---------------------------------------------------------------------------------------------------



    // ADD PRODUCT
    //---------------------------------------------------------------------------------------------------
    @Transactional
    public ResponseEntity<ApiResponse> addProduct(StockEntity data){
        boolean isGroupIdUsed = supplierRepository.existsByGroupId(data.getGroup_id());
        if(   !isGroupIdUsed   ){
            try{
                supplierRepository.saveAndFlush(data);
                return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Service Error!"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("Item already exists!"));
        }
    }
    //---------------------------------------------------------------------------------------------------



    // DELETE PRODUCT
    //---------------------------------------------------------------------------------------------------
    @Transactional
    public ResponseEntity<ApiResponse> deleteProduct(StockEntity data){
        boolean isItemExists = supplierRepository.existsById(data.getId());
        if(   isItemExists   ){
            try{
                boolean deleteControl = invokeDeleteControl(data);
                if(deleteControl){
                    return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
                }
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Service Error!"));
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Service Error!"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("The item doesn't exist!"));
        }
    }
    //---------------------------------------------------------------------------------------------------


    public boolean invokeDeleteControl(StockEntity data) {

        String url = "http://localhost:8082/operations/stock";

        Boolean result = webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(data)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // blocking for simplicity, consider reactive approaches in production

        return result;
    }
}
