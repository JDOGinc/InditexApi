package com.jdog.apirest.application.service;

import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryApplicationService {
    private final CategoryService categoryService;

    public CategoryApplicationService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Product> getSortedShirt(double salesWeight, double stockWeight) {
        if (salesWeight < 0 || stockWeight < 0) {
            throw new IllegalArgumentException("Negative Weight");
        }
        return categoryService.sortShirts(salesWeight, stockWeight);
    }
}
