package com.jdog.apirest.domain.service;

import com.jdog.apirest.domain.decorators.*;
import com.jdog.apirest.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductService() {
    }

    public double calculateScore(Product product, double salesWeight, double stockWeight) {

        if (salesWeight < 0 || stockWeight < 0 || product == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        ProductScore productScore = new ProductScoreStandard();
        productScore = new ProductScoreSalesCriterion(productScore, salesWeight);
        productScore = new ProductScoreStockCriterion(productScore, stockWeight);
        return productScore.calculateScore(product);
    }
}
