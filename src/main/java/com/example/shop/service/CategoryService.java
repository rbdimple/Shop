package com.example.shop.service;

import com.example.shop.dto.CategoryResponse;
import com.example.shop.dto.CreateCategoryRequest;
import com.example.shop.entity.Category;
import com.example.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories(){

        return categoryRepository.findAll()
                .stream()
                .map(category->
                        new CategoryResponse(category.getId(), category.getName()))
                .toList();
    }

    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest categoryRequest){

        Category category = new Category();
        category.setName(categoryRequest.getName());

        Category saved = categoryRepository.save(category);

        return new CategoryResponse(saved.getId(), saved.getName());
    }
}
