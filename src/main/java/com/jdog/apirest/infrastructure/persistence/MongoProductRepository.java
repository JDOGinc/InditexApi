package com.jdog.apirest.infrastructure.persistence;

import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository  extends MongoRepository<Product, Integer>, ProductRepository {


}
