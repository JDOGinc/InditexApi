package com.jdog.apirest.domain.model;

public class CriterionScoreImpl implements CriterionScore {
    protected CriterionScore criterionScore;
    public CriterionScoreImpl(CriterionScore criterionScore) {
        this.criterionScore = criterionScore;
    }
    @Override
    public double calculateScore(Product product) {
        return criterionScore.calculateScore(product);
    }
}
