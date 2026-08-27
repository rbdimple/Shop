package com.example.shop;

import com.example.shop.dto.CreateProductRequest;
import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.UpdateProductRequest;
import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProduct_success(){
        Product product = new Product();
        Category category = new Category();

        product.setId(67);
        product.setName("Razer Keyboard");
        product.setPrice(5120);
        product.setCost(4800);
        product.setStock(8);

        category.setId(1);
        category.setName("Keyboard");

        product.setCategory(category);

        when(productRepository.findById(1))
            .thenReturn(Optional.of(product));

        ProductResponse result = productService.getProduct(1);

        assertEquals(67,result.getId());
        assertEquals("Razer Keyboard",result.getName());
        assertEquals(5120,result.getPrice());
        assertEquals(8,result.getStock());
        assertEquals(1,result.getCategoryId());
        assertEquals("Keyboard",result.getCategoryName());
    }

    @Test
    void getProduct_notFound(){

        when(productRepository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,()->productService.getProduct(999));
    }

    @Test
    void createProduct_success(){

        CreateProductRequest createProductRequest =
                new CreateProductRequest("Dell P2725D",
                                            10800,
                                            9800,
                                            1,
                                            3
                );

        Category category = new Category();
        category.setId(3);
        category.setName("Monitor");

        when(categoryRepository.findById(3))
                .thenReturn(Optional.of(category));

        Product saved = new Product();
        saved.setId(65);
        saved.setName("Dell P2725D");
        saved.setPrice(10800);
        saved.setCost(9800);
        saved.setStock(1);
        saved.setCategory(category);

        when(productRepository.save(any(Product.class)))
                .thenReturn(saved);

        productService.createProduct(createProductRequest);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(captor.capture());

        Product product = captor.getValue();

        assertEquals("Dell P2725D",product.getName());
        assertEquals(10800,product.getPrice());
        assertEquals(9800,product.getCost());
        assertEquals(1,product.getStock());
        assertEquals(category,product.getCategory());
    }

    @Test
    void updateProduct_success(){

        UpdateProductRequest request = new UpdateProductRequest();
        request.setPrice(5512);

        Product product = new Product();
        product.setId(74);
        product.setName("2.5 SSD");
        product.setPrice(5400);
        product.setCost(5000);

        Category category = new Category();
        category.setId(5);
        category.setName("Storage");

        product.setCategory(category);

        when(productRepository.findById(74))
                .thenReturn(Optional.of(product));

        ProductResponse result = productService.updateProduct(74,request);

        assertEquals("2.5 SSD",result.getName());
        assertEquals(5512,result.getPrice());
        assertEquals(5,result.getCategoryId());
        assertEquals("Storage",result.getCategoryName());
    }

    @Test
    void deleteProduct_success(){

        Product product = new Product();
        product.setId(8);

        when(productRepository.findById(8))
                .thenReturn(Optional.of(product));

        productService.deleteProduct(8);

        verify(productRepository).delete(product);
    }
}
