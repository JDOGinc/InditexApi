package com.jdog.apirest.domain.decorators;

import com.jdog.apirest.domain.model.Product;
/**
 * Clase abstracta que representa un criterio de ordenación que se puede combinar con otro criterio.
 * Proporciona una implementación predeterminada para el método {@link #calculateScore(Product)}.
 */
abstract class ProductScoreDecorator implements ProductScore {
    protected ProductScore productScore;
    public ProductScoreDecorator(ProductScore productScore) {
        this.productScore = productScore;
    }
    @Override
    public double calculateScore(Product product) {
        return productScore.calculateScore(product);
    }
}
