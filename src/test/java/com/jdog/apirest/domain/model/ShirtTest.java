package com.jdog.apirest.domain.model;


import com.jdog.apirest.domain.exception.EmptyValueException;
import com.jdog.apirest.domain.exception.NegativeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ShirtTest {
    private final int id = 1;
    private final String name = "V-NECH BASIC SHIRT";
    private Shirt shirt;

    @BeforeEach
    void setUp() {
        // Act
        shirt = Shirt.createWithDefaultStock(id, name, "SHIRT");
    }
    @Test
    public void testProductCreation() {
        // Assert
        assertAll("Product properties should be set correctly",
                () -> assertEquals(this.id, shirt.getId()),
                () -> assertEquals(name, shirt.getName())
        );
    }
    @Test
    public void whenSetNegativeId_thenThrowsException() {
        assertThrows(IllegalArgumentException.class,  ()-> Shirt.createWithDefaultStock(-1, name,"SHIRT"));
    }

    @Test
    public void whenSetNullEmptyName_thenThrowsException(){
        String nullName = null;
        String emptyName = "";
        assertThrows(EmptyValueException.class, () -> shirt.setName(nullName));
        assertThrows(EmptyValueException.class, () -> shirt.setName(emptyName));
    }
    @Test
    public void whenSetNegativeStockSalesUnits_thenThrowsException() {
        assertThrows(NegativeValueException.class, () -> shirt.updateSalesUnits(-1));
    }
    @Test
    public void whenAddSizeStock_thenSizeStockMapContainsSize() {
        // Arrange
        Product productWithStock = Shirt.createWithStock(id, name,"SHIRT" ,0, new HashMap<>());
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
        shirt.addToMapSizeStock(size, stock);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> shirt.addToMapSizeStock(size, stock));
    }
    @Test
    public void whenUpdateSizeStock_thenThrowsException(){
        // Act & Assert
        assertThrows(NegativeValueException.class, () -> shirt.updateSizeStock(Size.S, -10));
    }
    @Test
    public void whenUpdateSizeStock_thenSizeStockMapContainsUpdatedStock(){
        // Arrange
        Size size = Size.M;
        int stock = 10;
        shirt.addToMapSizeStock(size, stock);
        int updatedStock = 20;

        // Act
        shirt.updateSizeStock(size, updatedStock);

        // Assert
        assertEquals(updatedStock, shirt.getSizesStockMap().get(size));
    }
}
