package com.example.shop.service;

import com.example.shop.dto.CreateProductRequest;
import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.UpdateProductRequest;
import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.exception.CategoryNotFoundException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService (ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllwithCategory()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getId(),
                        product.getCategory().getName()
                ))
                .toList();
    }

    @Transactional(readOnly  = true)
    public List<ProductResponse> getProductsByCategoryId(Integer categoryId){
        return productRepository.findByCategoryIdWithCategory(categoryId)
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getId(),
                        product.getCategory().getName()
                ))
                .toList();
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new CategoryNotFoundException(request.getCategoryId()));

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCost(request.getCost());
        product.setStock(request.getStock());
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStock(),
                saved.getCategory().getId(),
                saved.getCategory().getName());
    }

    @Transactional
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));

        if (request.getName() != null) {
            product.setName(request.getName());
        }

        if (request.getPrice() != null){
            product.setPrice(request.getPrice());
        }

        if (request.getCategoryId() != null){
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(()-> new CategoryNotFoundException(request.getCategoryId()));
            product.setCategory(category);
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId(),
                product.getCategory().getName());
    }

    @Transactional
    public void deleteProduct(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));

        productRepository.delete(product);
    }
}
