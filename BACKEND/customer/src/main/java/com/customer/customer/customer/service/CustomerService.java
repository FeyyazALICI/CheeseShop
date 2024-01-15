package com.customer.customer.customer.service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.customer.customer.entity.BasketEntity;
import com.customer.customer.customer.entity.CustomerEntity;
import com.customer.customer.customer.repository.BasketRepository;
import com.customer.customer.customer.repository.CustomerRespository;
import com.customer.customer.response.ApiResponse;
import jakarta.transaction.Transactional;

import org.springframework.web.reactive.function.client.WebClient;
// auxilary imports
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRespository customerRespository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private WebClient.Builder wBuilder;


    // GET ALL DATA
    // --------------------------------------------------------------------------------------
    public List<CustomerEntity> getAllData() {
        return customerRespository.findAll();
    }
    // --------------------------------------------------------------------------------------



    // REGISTRATION
    // --------------------------------------------------------------------------------------
    @Transactional
    public ResponseEntity<ApiResponse> register(CustomerEntity data) {
        // checking if the email is used
        boolean checkEmailUsage = customerRespository.existsByEmail(data.getEmail());
        if (!checkEmailUsage) {
            try {
                customerRespository.saveAndFlush(data);
                return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse("Internal Service Error!"));
            }
        }
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("The Email is Already in Use!"));
    }
    // --------------------------------------------------------------------------------------



    // DELETE USER
    // --------------------------------------------------------------------------------------
    @Transactional
    public ResponseEntity<ApiResponse> delete(CustomerEntity data) {
        // checking if the email is used
        boolean checkEmailUsage = customerRespository.existsByEmail(data.getEmail());
        if (checkEmailUsage) {
            try {
                customerRespository.delete(data);
                return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse("Internal Service Error!"));
            }
        }
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(new ApiResponse("The Email not found!"));
    }
    // --------------------------------------------------------------------------------------


    // BASKET
    //---------------------------------------------------------------------------------------------------
    public ResponseEntity<ApiResponse> basket(BasketEntity data){
        try{
            basketRepository.saveAndFlush(data);
            boolean result = invokeBasketControl(data);
            if(result){
                return ResponseEntity.status(HttpStatus.SC_OK).body(new ApiResponse("Succesfull Operation!"));
            }
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .body(new ApiResponse("Internal Service Error!"));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("Internal Service Error!"));
        }
    }
    //---------------------------------------------------------------------------------------------------




    
    public boolean invokeBasketControl(BasketEntity data) {

        String url = "http://localhost:8082/operations/basket";

        Boolean result = wBuilder.build()
                .post()
                .uri(url)
                .bodyValue(data)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // blocking for simplicity, consider reactive approaches in production
        return result;
    }
}
