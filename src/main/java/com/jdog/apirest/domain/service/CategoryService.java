package com.jdog.apirest.domain.service;
import com.jdog.apirest.domain.model.Category;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryService(CategoryRepository productRepository, ProductService productService) {
        this.categoryRepository = productRepository;
        this.productService = productService;
    }
    public List<Product> sortShirts(double salesWeight, double stockWeight) {

        if (salesWeight < 0 || stockWeight < 0) {
            throw new IllegalArgumentException("Negative Weight");
        }
        Category category;
        Optional<Category> categoryResult = categoryRepository.findByName("SHIRTS");
        if (categoryResult.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }else{
            category = categoryResult.get();
        }

        List<Product> products = category.getProducts();

        products = products.stream()
                .sorted(Comparator.comparingDouble((Product p) -> productService.calculateScore(p,salesWeight, stockWeight)).reversed())
                .collect(Collectors.toList());

        return products;
    }

}