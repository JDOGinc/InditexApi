package com.jdog.apirest.domain.model;

public class CriterionScoreZero implements CriterionScore {
    @Override
    public double calculateScore(Product product) {
        return 0;
    }
}
