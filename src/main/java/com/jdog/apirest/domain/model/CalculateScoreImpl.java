package com.jdog.apirest.domain.model;

public class CalculateScoreImpl implements CalculateScore {
    protected CalculateScore calculateScore;
    public CalculateScoreImpl(CalculateScore calculateScore) {
        this.calculateScore = calculateScore;
    }
    @Override
    public double calculateScore(Product product) {
        return calculateScore.calculateScore(product);
    }
}
