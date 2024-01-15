package com.jdog.apirest.domain.model;

public class CriterionScoreSalesCriterion extends CriterionScoreImpl {
    private double weight;
    public CriterionScoreSalesCriterion(CriterionScore criterionScore, double weight) {
        super(criterionScore);
        this.weight = weight;
    }
    @Override
    public double calculateScore(Product product) {
        return super.calculateScore(product) + (product.getSalesUnits() * weight);
    }
}
