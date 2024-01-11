package com.jdog.apirest.domain.decorators;

import com.jdog.apirest.domain.model.Product;

public class ProductScoreStandard implements ProductScore{
    @Override
    public double calculateScore(Product product) {
        return 0;
    }
}
