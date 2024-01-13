package com.jdog.apirest.infrastructure.dtos;

import com.jdog.apirest.domain.model.Size;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

public class StockDto {
    @Field("sizes")
    private Map<String, Integer> sizesStock;


    // Constructor
    public StockDto() {
    }

    public Map<String, Integer> getSizesStock() {
        return sizesStock;
    }

    public void setSizesStock(Map<String, Integer> sizesStock) {
        this.sizesStock = sizesStock;
    }
}