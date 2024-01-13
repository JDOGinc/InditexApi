package com.jdog.apirest.infrastructure.dtos;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Products")
public class ProductDto {
    private int id;
    private String name;
    @Field("sales_units")
    private int salesUnits;
    private StockDto stock;
    public ProductDto() {
    }

    public int getId() {
        return id;
    }

    public int getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(int salesUnits) {
        this.salesUnits = salesUnits;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StockDto getStock() {
        return stock;
    }

    public void setStock(StockDto stock) {
        this.stock = stock;
    }
}
