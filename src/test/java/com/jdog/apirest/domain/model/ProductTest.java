package com.jdog.apirest.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        Map<Size, Integer> stock = new HashMap<>();

        stock.put(Size.S, 10);
        stock.put(Size.M, 20);
        stock.put(Size.L, 30);
        product = Product.create(1, "TestProduct", stock, 100);
    }

    @Test
    void create() {
        double score = 100 + 60;
        assertEquals(1, product.getId());
        assertEquals("TestProduct", product.getName());
        assertEquals(10, product.getStock().get(Size.S));
        assertEquals(20, product.getStock().get(Size.M));
        assertEquals(30, product.getStock().get(Size.L));
        assertEquals(100, product.getSalesUnits());
        assertEquals(score, product.getScore());
    }

    @Test
    void calculateScoreByStockSales() {
        CriterionScore criteria = new CriterionScoreDefault();
        criteria = new CriterionScoreSalesCriterion(criteria, 1);
        criteria = new CriterionScoreStockCriterion(criteria, 1);

        product.calculateScoreByCriteria(criteria);
        assertEquals(160, product.getScore());
    }
    @Test
    void calculateTotalStockCount() {
        assertEquals(60, product.calculateTotalStockCount());
    }
}