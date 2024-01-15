package com.jdog.apirest.domain.model;

public class CriterionScoreStockCriterion extends CriterionScoreImpl {
    private double weight;
    public CriterionScoreStockCriterion(CriterionScore criterionScore, double weight) {
        super(criterionScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + product.calculateTotalStockCount() * weight;
    }
}
