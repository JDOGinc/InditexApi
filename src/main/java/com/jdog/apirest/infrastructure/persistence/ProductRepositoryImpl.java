package com.jdog.apirest.infrastructure.persistence;

import com.jdog.apirest.infrastructure.dtos.ProductDto;
import com.jdog.apirest.infrastructure.mapper.ProductMapper;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoProductRepository;
    private final ProductMapper productMapper;
    public ProductRepositoryImpl( MongoProductRepository mongoProductRepository, ProductMapper productMapper) {
        this.mongoProductRepository = mongoProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll(double salesWeight , double stockWeight) {
        List<ProductDto> products = mongoProductRepository.findAll();

        return products.stream()
                .map(productMapper::toDomain)
                .sorted(Comparator.comparingDouble((Product p) -> {
                    p.calculateScoreByStockSales(salesWeight, stockWeight);
                    return p.getScore();
                }).reversed()).collect(Collectors.toList());
    }
}
