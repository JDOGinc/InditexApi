package com.jdog.apirest.application.usecase;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortProductsUseCase {
    private final ProductRepository productRepository;
    @Autowired
    public SortProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute(double salesWeight, double stockWeight) {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .sorted(Comparator.comparingDouble((Product p) -> {
                    p.setProductScore(salesWeight, stockWeight);
                    return p.getProductScore();
                }).reversed())
                .collect(Collectors.toList());
    }
}
