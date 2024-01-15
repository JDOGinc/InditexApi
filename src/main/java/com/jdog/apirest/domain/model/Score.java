package com.jdog.apirest.domain.model;

public class Score {
    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score CreateScoreByCriteria(Product product, CriterionScore criteria) {
        return new Score(criteria.calculateScore(product));
    }
    public double getScore() {
        return score;
    }

    private static void validateWeight(double firstWeight, double secondWeight) {
        double minWeight = 0;
        if (firstWeight < minWeight || secondWeight < minWeight) {
            throw new IllegalArgumentException("Invalid weight, cannot be negative");
        }
    }
}
