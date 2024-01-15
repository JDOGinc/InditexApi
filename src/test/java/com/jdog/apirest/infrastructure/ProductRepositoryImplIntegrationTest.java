package com.jdog.apirest.infrastructure;

import com.jdog.apirest.domain.model.*;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryImplIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAllReturnsSortedProducts() {
        CriterionScore criteria = new CriterionScoreDefault();
        criteria = new CriterionScoreSalesCriterion(criteria, 1);
        criteria = new CriterionScoreStockCriterion(criteria, 1);

        int productIdWithHighestScore = 5;
        List<Product> products = productRepository.findAll(criteria);

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(productIdWithHighestScore, products.get(0).getId());

    }
}
