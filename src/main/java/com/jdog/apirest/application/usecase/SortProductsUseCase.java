package com.jdog.apirest.application.usecase;
import com.jdog.apirest.domain.model.CriterionScore;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SortProductsUseCase {
    private final ProductRepository productRepository;

    public SortProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute(CriterionScore criteria) {
        return  productRepository.findAll(criteria);
    }
}
