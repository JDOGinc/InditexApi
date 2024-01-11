package com.jdog.apirest.domain.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "products")
@TypeAlias("shirt")
public class Shirt extends Product {
    private String type;

    private Shirt() {
        super();
    }
    private Shirt(int id, String name, String type) {
        super(id, name);
        this.type = type;
    }
    private Shirt(int id, String name, String type, Map<Size, Integer> sizesStockMap, int salesUnits) {
        super(id, name, sizesStockMap, salesUnits);
        this.type = type;
    }

    public static Shirt createWithStock(int id, String name, String type, int salesUnits, Map<Size,Integer> sizesStockMap) {
        if (id < 0) throw new IllegalArgumentException("Invalid id");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid name");

        return new Shirt(id, name, type, sizesStockMap, salesUnits);
    }

    public static Shirt createWithDefaultStock(int id, String name, String type){
        if (id < 0) throw new IllegalArgumentException("Invalid id");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid name");
        return new Shirt(id, name, type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
