package com.jdog.apirest.domain.service;

import com.jdog.apirest.domain.model.Category;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.model.Shirt;
import com.jdog.apirest.domain.model.Size;
import com.jdog.apirest.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

class CategoryServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepository, productService);
        Map<Size,Integer> sizesStockMap1 = new HashMap<>(){{
            put(Size.S, 4);
            put(Size.M, 9);
            put(Size.L, 0);
        }};
        Product product1 = Shirt.createWithStock(1, "V-NECH BASIC SHIRT", "SHIRT" ,100, sizesStockMap1);
        Map<Size,Integer> sizesStockMap2 = new HashMap<>(){{
            put(Size.S, 35);
            put(Size.M, 9);
            put(Size.L, 9);
        }};
        Product product2 = Shirt.createWithStock(2, "CONTRASTING FABRIC T-SHIRT", "SHIRT", 50, sizesStockMap2);

        Map<Size,Integer> sizesStockMap3 = new HashMap<>(){{
            put(Size.S, 20);
            put(Size.M, 2);
            put(Size.L, 20);
        }};
        Product product3 = Shirt.createWithStock(3, "RAISED PRINT T-SHIRT", "SHIRT", 80, sizesStockMap3);

        Map<Size,Integer> sizesStockMap4 = new HashMap<>(){{
            put(Size.S, 25);
            put(Size.M, 30);
            put(Size.L, 10);
        }};
        Product product4 = Shirt.createWithStock(4, "PLEATED T-SHIRT", "SHIRT", 3, sizesStockMap4);

        Map<Size,Integer> sizesStockMap5 = new HashMap<>(){{
            put(Size.S, 0);
            put(Size.M, 1);
            put(Size.L, 0);
        }};
        Product product5 = Shirt.createWithStock(5, "CONTRASTING LACE T-SHIRT", "SHIRT", 650, sizesStockMap5);

        List<Product> productShirtsList = Arrays.asList(product1, product2, product3, product4, product5);

        Category shirtCategory = new Category("cat001", "SHIRTS", productShirtsList,"A variety of shirts for all occasions");


        lenient().when(categoryRepository.findByName("SHIRTS")).thenReturn(Optional.of(shirtCategory));

    }

    @Test
    public void whenSortShirtsList_thenReturnSortedShirtsList() {
        List<Product> sortedList = categoryService.sortShirts(2,2);

        assertEquals(sortedList.get(0).getId(), 5);
        assertNotNull(sortedList);
        assertFalse(sortedList.isEmpty());
    }
}