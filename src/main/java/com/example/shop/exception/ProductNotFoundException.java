package com.example.shop.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id){
        super("Product not found:" + id);
    }
}
