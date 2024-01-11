package com.jdog.apirest.domain.service;

import com.jdog.apirest.domain.decorators.*;
import com.jdog.apirest.domain.model.Product;
import org.springframework.stereotype.Service;


public interface ProductService {

    public double calculateScore(Product product, double salesWeight, double stockWeight);
}
