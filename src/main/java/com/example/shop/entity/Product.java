package com.example.shop.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer price;
    private Integer cost;
    private Integer stock;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getId() {
        return this.id;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getName() { return this.name; }

    public Integer getCost() { return this.cost; }

    public Integer getStock() {
        return this.stock;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public void setCost(Integer cost){
        this.cost = cost;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @PrePersist
    public void onCreate(){
        LocalDateTime nowTime = LocalDateTime.now();
        this.createTime = nowTime;
        this.updateTime = nowTime;
    }

    @PreUpdate
    public void onUpdate(){
       this.updateTime = LocalDateTime.now();
    }

}
