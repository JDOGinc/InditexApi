package com.jdog.apirest.domain.model;

import java.util.Map;

public class Product {
    private int id;
    private String name;
    private Map<Size, Integer> stock;
    private int salesUnits;
    private double productScore;
    private Product() {
    }

    private Product(int id, String name, Map<Size, Integer> stock, int salesUnits) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.salesUnits = salesUnits;
        this.productScore = 0;
    }
    public static Product create(int id, String name, Map<Size, Integer> stock, int salesUnits) {
        return new Product(id, name, stock, salesUnits);
    }

    public int getId() {
        return id;
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

    public Map<Size, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Size, Integer> stock) {
        this.stock = stock;
    }

    public int getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(int salesUnits) {
        this.salesUnits = salesUnits;
    }

    public void setProductScore(double salesWeight, double stockWeight) {
        Score productScore = Score.CreateScoreByStockSalesWeight(this, stockWeight, salesWeight);
        this.productScore = productScore.getScore();

    }

    public double getProductScore() {
        return productScore;
    }
    public double calculateTotalStockCount() {
        return stock.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static class Stock {
        private Map<Size, Integer> sizesStock;
        // Constructor
        public Stock() {
        }
    }

}
