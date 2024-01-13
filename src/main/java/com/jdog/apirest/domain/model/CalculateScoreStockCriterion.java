package com.jdog.apirest.domain.model;

public class CalculateScoreStockCriterion extends CalculateScoreImpl {
    private double weight;
    public CalculateScoreStockCriterion(CalculateScore calculateScore, double weight) {
        super(calculateScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + product.calculateTotalStockCount() * weight;
    }
}
