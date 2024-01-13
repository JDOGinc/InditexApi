package com.jdog.apirest.domain.repository;
import com.jdog.apirest.domain.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll(double salesWeight, double stockWeight);

}
