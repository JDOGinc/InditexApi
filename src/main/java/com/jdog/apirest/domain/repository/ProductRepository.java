package com.jdog.apirest.domain.repository;
import com.jdog.apirest.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

}
