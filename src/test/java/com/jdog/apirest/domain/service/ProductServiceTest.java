package com.jdog.apirest.domain.service;

import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.model.Size;
import com.jdog.apirest.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

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


        List<Product> productsList = Arrays.asList(product1, product2, product3, product4, product5);

        lenient().when(productRepository.findAll()).thenReturn(productsList);
    }

    @Test
    public void whenSortProducts_thenReturnsSorted() {

        // Act
        List<Product> sortedProducts = productService.sortProducts(1.0, 1.0);

        // Assert
        assertThat(sortedProducts, is(not(empty())));
        // verify that the first product is the one with the highest score
        assertThat(sortedProducts.get(0).getName(), is("CONTRASTING LACE T-SHIRT"));
    }
    @Test
    public void whenOneWeightIsZero_thenSortedByTheOtherWeight() {
        // Act
        List<Product> sortedProducts = productService.sortProducts(0.0, 1.0);

        // Assert
        assertThat(sortedProducts, is(not(empty())));
        assertThat(sortedProducts.get(0).getName(), is("PLEATED T-SHIRT"));
    }
    @Test
    public void whenWeightIsNegative_thenThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.sortProducts(-1.0, 1.0);
        });
    }
    @Test
    public void whenProductListIsEmpty_thenReturnsEmptyList() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Product> sortedProducts = productService.sortProducts(1.0, 1.0);

        // Assert
        assertThat(sortedProducts, is(empty()));
    }
}
