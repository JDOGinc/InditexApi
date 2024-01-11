package com.jdog.apirest.infrastructure.web;

import com.jdog.apirest.application.service.CategoryApplicationService;
import com.jdog.apirest.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CategoryApplicationService categoryApplicationService;

    public ProductController(CategoryApplicationService categoryApplicationService) {
        this.categoryApplicationService = categoryApplicationService;
    }
    @GetMapping("/sortShirts")
    public List<Product> sortProducts(@RequestParam double salesWeight, @RequestParam double stockWeight){
        try {
            return categoryApplicationService.getSortedShirt(salesWeight, stockWeight);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Negative Weight");
        }
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
