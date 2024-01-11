package com.jdog.apirest.domain.decorators;

import com.jdog.apirest.domain.model.Product;
/**
 * Interfaz que define el criterio de ordenación para los productos.
 * Los implementadores de esta interfaz proporcionarán los métodos necesarios
 * para calcular una puntuación para un producto basada en un criterio específico.
 */
public interface ProductScore {
    double calculateScore(Product product);
}
