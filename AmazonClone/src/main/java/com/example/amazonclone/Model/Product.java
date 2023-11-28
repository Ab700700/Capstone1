package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "Id should not be empty")
    private String id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should be at least 4 characters")
    private String name;
    @NotNull(message = "Price should not be empty")
    @Positive(message = "Price should be a positive number")
    private double price;
    @NotEmpty(message = "Category id should not be empty")
    private String categoryID;
}
