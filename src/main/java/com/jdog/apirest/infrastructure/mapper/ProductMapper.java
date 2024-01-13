package com.jdog.apirest.infrastructure.mapper;

import com.jdog.apirest.infrastructure.dtos.ProductDto;
import com.jdog.apirest.infrastructure.dtos.StockDto;
import com.jdog.apirest.domain.model.Product;
import com.jdog.apirest.domain.model.Size;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
@Component
public class ProductMapper {

    public Product toDomain(ProductDto productDto) {

        StockDto stockDto = productDto.getStock();
        Map<Size, Integer> sizesStock = stockDto.getSizesStock()
                .entrySet().stream()
                .collect(Collectors.toMap(e -> Size.valueOf(e.getKey()), Map.Entry::getValue));

        return Product.create(productDto.getId(), productDto.getName(), sizesStock, productDto.getSalesUnits());

    }
}
