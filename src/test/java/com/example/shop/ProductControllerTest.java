package com.example.shop;

import com.example.shop.controller.ProductController;
import com.example.shop.dto.CreateProductRequest;
import com.example.shop.dto.ProductResponse;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.service.ProductService;
import net.bytebuddy.agent.VirtualMachine;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)

public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void getProduct_returns200() throws Exception {

        ProductResponse productResponse =
                new ProductResponse(21,
                        "Trackball Mouse",
                        3850,
                        16,
                        2,
                        "Mouse");

        when(productService.getProduct(21))
                .thenReturn(productResponse);

        mockMvc.perform(
                get("/products/21")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(21))
                .andExpect(jsonPath("$.name").value("Trackball Mouse"))
                .andExpect(jsonPath("$.price").value(3850))
                .andExpect(jsonPath("$.stock").value(16))
                .andExpect(jsonPath("$.categoryId").value(2))
                .andExpect(jsonPath("$.categoryName").value("Mouse"));
    }

    @Test
    void productNotFound_returns404() throws Exception {

        when(productService.getProduct(999))
                .thenThrow(new ProductNotFoundException(999));

        mockMvc.perform(get("/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product not found:999"));
    }

    @Test
    void createProduct_returns201() throws Exception {

        ProductResponse response = new ProductResponse(21,
                "Trackball Mouse",
                3850,
                16,
                2,
                "Mouse");

        when(productService.createProduct(any(CreateProductRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"Trackball Mouse",
                                "price":3850,
                                "cost":3200,
                                "stock":16,
                                "categoryId":2
                            }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Trackball Mouse"))
                .andExpect(jsonPath("$.price").value(3850))
                .andExpect(jsonPath("$.stock").value(16))
                .andExpect(jsonPath("$.categoryId").value(2))
                .andExpect(jsonPath("$.categoryName").value("Mouse"));
    }

    @Test
    void invalidCreateRequest_returns400() throws Exception{

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name":"",
                        "price":-1,
                        "cost":-1,
                        "stock":-100,
                        "categoryId":0
                    }
                        """))
                .andExpect(status().isBadRequest());
    }
}
