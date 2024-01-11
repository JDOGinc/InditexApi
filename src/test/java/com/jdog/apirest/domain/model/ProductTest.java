package com.jdog.apirest.domain.model;


import com.jdog.apirest.domain.exception.EmptyValueException;
import com.jdog.apirest.domain.exception.NegativeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private final int id = 1;
    private final String name = "V-NECH BASIC SHIRT";
    private Product product;

    @BeforeEach
    void setUp() {
        // Act
        product = Product.createWithDefaultStock(id, name);
    }
    @Test
    public void testProductCreation() {
        // Assert
        assertAll("Product properties should be set correctly",
                () -> assertEquals(this.id, product.getId()),
                () -> assertEquals(name, product.getName())
        );
    }
    @Test
    public void whenSetNegativeId_thenThrowsException() {
        assertThrows(IllegalArgumentException.class,  ()-> {Product.createWithDefaultStock(-1, name);});
    }

    @Test
    public void whenSetNullEmptyName_thenThrowsException(){
        String nullName = null;
        String emptyName = "";
        assertThrows(EmptyValueException.class, () -> product.setName(nullName));
        assertThrows(EmptyValueException.class, () -> product.setName(emptyName));
    }
    @Test
    public void whenSetNegativeStockSalesUnits_thenThrowsException() {
        assertThrows(NegativeValueException.class, () -> product.updateSalesUnits(-1));
    }
    @Test
    public void whenAddSizeStock_thenSizeStockMapContainsSize() {
        // Arrange
        Product productWithStock = Product.createWithStock(id, name, 0, new HashMap<>());
        Size size = Size.M;


        // Act
        productWithStock.addToMapSizeStock(size, 100);

        // Assert
        assertTrue(productWithStock.getSizesStockMap().containsKey(size));
    }
    @Test
    public void whenAddDuplicateSizeStock_thenThrowsException(){
        // Arrange
        Size size = Size.M;
        int stock = 10;
        product.addToMapSizeStock(size, stock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> product.addToMapSizeStock(size, stock));
    }
    @Test
    public void whenUpdateSizeStock_thenThrowsException(){
        // Act & Assert
        assertThrows(NegativeValueException.class, () -> product.updateSizeStock(Size.S, -10));
    }
    @Test
    public void whenUpdateSizeStock_thenSizeStockMapContainsUpdatedStock(){
        // Arrange
        Size size = Size.M;
        int stock = 10;
        product.addToMapSizeStock(size, stock);
        int updatedStock = 20;

        // Act
        product.updateSizeStock(size, updatedStock);

        // Assert
        assertEquals(updatedStock, product.getSizesStockMap().get(size));
    }
}
