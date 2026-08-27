package com.example.shop.dto;

import jakarta.validation.constraints.*;

public class UpdateProductRequest {

    private String name;

    @PositiveOrZero
    private Integer price;

    @Positive
    private Integer categoryId;

    public String getName(){
        return this.name;
    }

    public Integer getPrice(){
        return this.price;
    }

    public Integer getCategoryId(){
        return this.categoryId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }
}
