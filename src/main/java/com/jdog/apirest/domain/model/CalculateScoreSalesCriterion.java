package com.jdog.apirest.domain.model;

public class CalculateScoreSalesCriterion extends CalculateScoreImpl {
    private double weight;
    public CalculateScoreSalesCriterion(CalculateScore calculateScore, double weight) {
        super(calculateScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + (product.getSalesUnits() * weight);
    }
}
