package com.jdog.apirest.domain.model;

import java.util.Map;

public class Product {
    private int id;
    private String name;
    private Map<Size, Integer> stock;
    private int salesUnits;
    private double score;
    private Product() {
    }
    private Product(int id, String name, Map<Size, Integer> stock, int salesUnits) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.salesUnits = salesUnits;
        this.score = salesUnits + calculateTotalStockCount();
    }
    public static Product create(int id, String name, Map<Size, Integer> stock, int salesUnits) {
        return new Product(id, name, stock, salesUnits);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Size, Integer> getStock() {
        return stock;
    }

    public int getSalesUnits() {
        return salesUnits;
    }

    public double getScore() {
        return score;
    }
    public void calculateScoreByCriteria(CriterionScore criteria) {
        Score productScore = Score.CreateScoreByCriteria(this, criteria);
        this.score = productScore.getScore();
    }
    public double calculateTotalStockCount() {
        return stock.values().stream().mapToInt(Integer::intValue).sum();
    }

}
