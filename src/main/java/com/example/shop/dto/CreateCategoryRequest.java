package com.example.shop.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequest {

    @NotBlank
    private String name;

    public String getName(){ return this.name; }
}
