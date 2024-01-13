package com.jdog.apirest.infrastructure.dtos;

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

    public String getName() {
        return name;
    }

    public StockDto getStock() {
        return stock;
    }

}
