package com.example.shop.dto;

import com.example.shop.repository.CategoryRepository;

public class CategoryResponse {

    private Integer id;
    private String name;

    public CategoryResponse(){};
    public CategoryResponse(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){ return this.id; }
    public String getName(){ return this.name; }


}
