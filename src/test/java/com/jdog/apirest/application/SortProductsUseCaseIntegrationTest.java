package com.jdog.apirest.application;

import com.jdog.apirest.application.usecase.SortProductsUseCase;
import com.jdog.apirest.domain.model.Product;
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
        double salesWeight = 0.5;
        double stockWeight = 0.5;
        int productIdWithHighestScore = 5;
        List<Product> products = sortProductsUseCase.execute(salesWeight, stockWeight);

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(productIdWithHighestScore, products.get(0).getId());
    }

    @Test
    public void whenExecuteWithNegativeWeight_thenGetException() {
        assertThrows(IllegalArgumentException.class, () -> {
            sortProductsUseCase.execute(-1, -1);
        });
    }
}