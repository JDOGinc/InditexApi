package com.jdog.apirest.domain.model;
import com.jdog.apirest.domain.exception.EmptyValueException;
import com.jdog.apirest.domain.exception.NegativeValueException;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Product class, contains the product id, name and stock
 * represents a product in the system
 */
@Document(collection = "Products")
@AccessType(AccessType.Type.FIELD)
public class Product {
    private int id;
    private String name;
    private Stock stock;

    // Constructor
    protected Product() {
    }
    protected Product(int id, String name) {
        setId(id);
        this.name = name;
        createDefaultStock();
    }
    protected Product (int id, String name, Map<Size, Integer> sizesStockMap, int salesUnits) {
        setId(id);
        this.name = name;
        this.stock = new Stock(sizesStockMap, salesUnits);
    }
    private void createDefaultStock(){
        Map<Size, Integer> sizesStockMap = Arrays.stream(Size.values())
                .collect(Collectors.toMap(size -> size, size -> 0));
        this.stock = new Stock(sizesStockMap, 0);
    }


    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id < 0) throw new IllegalArgumentException("Invalid id");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new EmptyValueException("Invalid name");
        this.name = name;
    }

    // Stock methods - Aggregation
    public Map<Size, Integer> getSizesStockMap() {

        return stock.getSizesStock();
    }
    public void  addToMapSizeStock(Size size, int stock){
        checkDuplicateSize(size);
        //add size and stock to map
        this.stock.getSizesStock().put(size, stock);
    }
    public void updateSizeStock(Size size, int stock){
        //validate if size is null, not empty, no duplicated
        if(size == null || size.getName().isEmpty() || !this.stock.getSizesStock().containsKey(size))
            throw new IllegalArgumentException("Invalid size");
        //validate if stock is negative
        if(stock < 0)
            throw new NegativeValueException("Invalid stock");
        //update determined size
        this.stock.getSizesStock().replace(size, stock);
    }
    public void updateSalesUnits(int salesUnits){
        if(salesUnits < 0)
            throw new NegativeValueException("Invalid sales units");
        this.stock.setSalesUnits(salesUnits);
    }
    private void checkDuplicateSize(Size size){
        if(this.stock.getSizesStock().containsKey(size))
            throw new IllegalArgumentException("Duplicate size in map");
    }
    public int getSalesUnits() {
        return stock.getSalesUnits();
    }
    public int calculateTotalStockCount() {
        return stock.getSizesStock().values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    // Inner class static Stock - Aggregation

    /**
     * Stock class Aggregation, contains a map of sizes and stock and the total sales units
     * only accessible from {@link Product} class
     */
    private static class Stock {
        @Field("sizes")
        private Map<Size, Integer> sizesStock;
        @Field("sales_units")
        private int salesUnits;

        // Constructor
        public Stock() {
        }

        public Stock(Map<Size, Integer> sizesStock, int salesUnits) {
            this.sizesStock = sizesStock;
            this.salesUnits = salesUnits;
        }

        // Getters y Setters
        public Map<Size, Integer> getSizesStock() {
            return sizesStock;
        }


        public int getSalesUnits() {
            return salesUnits;
        }

        public void setSalesUnits(int salesUnits) {
            this.salesUnits = salesUnits;
        }
    }

}
