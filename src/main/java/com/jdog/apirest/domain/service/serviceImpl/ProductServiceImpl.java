package com.jdog.apirest.domain.service.serviceImpl;

import com.jdog.apirest.domain.decorators.ProductScore;
import com.jdog.apirest.domain.decorators.ProductScoreSalesCriterion;
import com.jdog.apirest.domain.decorators.ProductScoreStandard;
import com.jdog.apirest.domain.decorators.ProductScoreStockCriterion;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
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
