package com.example.shop.dto;

import jakarta.validation.constraints.*;

public class CreateProductRequest {
    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer price;

    @NotNull
    @PositiveOrZero
    private Integer cost;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotNull
    @Positive
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public Integer getPrice(){
        return price;
    }

    public Integer getCost(){
        return cost;
    }

    public Integer getStock(){
        return stock;
    }

    public Integer getCategoryId(){
        return categoryId;
    }
}
