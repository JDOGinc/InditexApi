package com.jdog.apirest.infrastructure.persistence;

import com.jdog.apirest.infrastructure.dtos.ProductDto;
import com.jdog.apirest.infrastructure.mapper.ProductMapper;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoProductRepository;
    private final ProductMapper productMapper;
    public ProductRepositoryImpl( MongoProductRepository mongoProductRepository, ProductMapper productMapper) {
        this.mongoProductRepository = mongoProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() {
        List<ProductDto> products = mongoProductRepository.findAll();
        return products.stream().map(productMapper::toDomain).toList();
    }
}
