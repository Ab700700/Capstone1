package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "Id should not be empty")
    private String id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should at least be 4 characters")
    private String name;
}
