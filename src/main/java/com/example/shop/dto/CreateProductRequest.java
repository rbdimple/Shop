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

    public CreateProductRequest(){}

    public CreateProductRequest(String name,
                                Integer price,
                                Integer cost,
                                Integer stock,
                                Integer categoryId){
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public String getName() { return name; }

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
