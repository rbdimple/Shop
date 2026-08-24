package com.example.shop.dto;

public class ProductResponse {
    public ProductResponse (Integer id,
                            String name,
                            Integer price,
                            Integer stock,
                            Integer categoryId,
                            String categoryName){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;
    private Integer categoryId;
    private String categoryName;
}
