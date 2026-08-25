package com.example.shop.controller;

import com.example.shop.dto.CreateProductRequest;
import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.UpdateProductRequest;
import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Integer categoryId
    ){
        if (categoryId == null){
            return ResponseEntity.ok(productService.getAllProducts());
        }
        else {
            return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(createProductRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id,
                                                         @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(productService.updateProduct(id,updateProductRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
