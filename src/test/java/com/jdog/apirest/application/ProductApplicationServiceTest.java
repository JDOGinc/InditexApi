package com.jdog.apirest.application;

import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.model.Size;
import com.jdog.apirest.domain.repository.ProductRepository;
import com.jdog.apirest.domain.service.CategoryService;
import com.jdog.apirest.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationServiceTest {
    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductApplicationService productApplicationService;

    private List<Product> productList;

    @BeforeEach
    void setUp() {

        Map<Size,Integer> sizesStockMap1 = new HashMap<>(){{
            put(Size.S, 4);
            put(Size.M, 9);
            put(Size.L, 0);
        }};
        Product product1 = Product.createWithStock(1, "V-NECH BASIC SHIRT", 100, sizesStockMap1);


        Map<Size,Integer> sizesStockMap2 = new HashMap<>(){{
            put(Size.S, 35);
            put(Size.M, 9);
            put(Size.L, 9);
        }};
        Product product2 = Product.createWithStock(2, "CONTRASTING FABRIC T-SHIRT", 50, sizesStockMap2);

        Map<Size,Integer> sizesStockMap3 = new HashMap<>(){{
            put(Size.S, 20);
            put(Size.M, 2);
            put(Size.L, 20);
        }};
        Product product3 = Product.createWithStock(3, "RAISED PRINT T-SHIRT", 80, sizesStockMap3);

        Map<Size,Integer> sizesStockMap4 = new HashMap<>(){{
            put(Size.S, 25);
            put(Size.M, 30);
            put(Size.L, 10);
        }};
        Product product4 = Product.createWithStock(4, "PLEATED T-SHIRT", 3, sizesStockMap4);

        Map<Size,Integer> sizesStockMap5 = new HashMap<>(){{
            put(Size.S, 0);
            put(Size.M, 1);
            put(Size.L, 0);
        }};
        Product product5 = Product.createWithStock(5, "CONTRASTING LACE T-SHIRT", 650, sizesStockMap5);


        productList = Arrays.asList(product1, product2, product3, product4, product5);
        lenient().when(productRepository.findAll()).thenReturn(productList);

        productService = new ProductService();
        productApplicationService = new ProductApplicationService(productService, categoryService);
    }

    @Test
    public void whenValidWeights_thenProductsShouldBeSorted() {
        // Act
        List<Product> result = productApplicationService.getSortedProducts(2.0, 1.0);

        // Assert
        assertThat(result.get(0).getName() ,is("CONTRASTING LACE T-SHIRT"));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(productList.size(), result.size());
    }
    @Test
    public void whenWeightIsNegative_thenThrowsException() {
        // Assert - Act
        int negativeWeight = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            productApplicationService.getSortedProducts(negativeWeight, negativeWeight);
        });
    }
}
