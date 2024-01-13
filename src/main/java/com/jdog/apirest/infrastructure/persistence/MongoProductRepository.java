package com.jdog.apirest.infrastructure.persistence;
import com.jdog.apirest.infrastructure.dtos.ProductDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository extends MongoRepository<ProductDto, Integer> {
}
