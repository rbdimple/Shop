package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Integer> {
    @Query("""
        SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category 
""")
    List<Product> findAllwithCategory();
}