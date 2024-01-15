package com.jdog.apirest.application;

import com.jdog.apirest.application.usecase.SortProductsUseCase;
import com.jdog.apirest.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SortProductsUseCaseIntegrationTest {



    @Autowired
    private SortProductsUseCase sortProductsUseCase;
    @Test
    public void testExecuteIntegration_thenReturnSortedList() {
        CriterionScore criteria = new CriterionScoreDefault();
        criteria = new CriterionScoreSalesCriterion(criteria, 1);
        criteria = new CriterionScoreStockCriterion(criteria, 1);

        double productIdWithHighestScore = 5;
        List<Product> products = sortProductsUseCase.execute(criteria);

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(productIdWithHighestScore, products.get(0).getId());
    }
}