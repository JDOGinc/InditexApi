package com.jdog.apirest.domain.decorators;

import com.jdog.apirest.domain.model.Product;
/**
 * Clase que representa un criterio de ordenación de tipo ratio stock.
 * Proporciona una implementación predeterminada para el método {@link #calculateScore(Product)}.
 */
public class ProductScoreStockCriterion extends ProductScoreDecorator {
    private double weight;
    public ProductScoreStockCriterion( ProductScore productScore ,double weight) {
        super(productScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + product.calculateTotalStockCount() * weight;
    }
}
