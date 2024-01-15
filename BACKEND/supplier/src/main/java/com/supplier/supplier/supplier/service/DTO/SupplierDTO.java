package com.supplier.supplier.supplier.service.DTO;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDTO {
    
    @JsonProperty("id")
    @Id
    private Long id;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("sign")
    private String sign;




}

