package com.example.amazonclone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "Id must not be empty")
    private String id;
    @NotEmpty(message = "Product id must not be empty")
    private String productid;
    @NotEmpty(message = "Merchant id must not be empty")
    private String merchantid;
    @NotNull(message = "stock must not be empty")
    private int stock;
}
