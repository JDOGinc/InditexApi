package com.jdog.apirest.domain.service;

import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.model.Shirt;
import com.jdog.apirest.domain.model.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {

        Map<Size,Integer> sizesStockMap = new HashMap<>(){{
            put(Size.S, 5);
            put(Size.M, 5);
            put(Size.L, 0);
        }};
        product = Shirt.createWithStock(1, "V-NECH BASIC SHIRT", "SHIRT", 100, sizesStockMap);

    }

    @Test
    public void whenCalculateScore_thenReturnScore() {

        // Act
        double score = productService.calculateScore(product, 2, 2);
        double expectedScore = 220;
        // Assert
        assertThat(score, is(expectedScore));
    }

    @Test
    public void whenCalculateScoreWithNegativeSalesWeight_thenThrowIllegalArgumentException() {

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> productService.calculateScore(product, -1, 2));
    }
    @Test
    public void whenCalculateScoreWithNegativeStockWeight_thenThrowIllegalArgumentException() {

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> productService.calculateScore(product, 2, -1));
    }
    @Test
    public void whenCalculateScoreWithNullProduct_thenThrowIllegalArgumentException() {

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> productService.calculateScore(null, 2, 2));
    }
    @Test
    public void whenCalculateScoreWithNullProductAndNegativeWeights_thenThrowIllegalArgumentException() {

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> productService.calculateScore(null, -1, -1));
    }
}
