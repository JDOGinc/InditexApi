package com.jdog.apirest.domain.decorators;

import com.jdog.apirest.domain.model.Product;
/**
 * Clase que representa un criterio de ordenación de tipo ventas por unidades.
 * Proporciona una implementación predeterminada para el método {@link #calculateScore(Product)}.
 */
public class ProductScoreSalesCriterion extends ProductScoreDecorator {
    private double weight;
    public ProductScoreSalesCriterion(ProductScore productScore, double weight) {
        super(productScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + (product.getSalesUnits() * weight);
    }
}
