package com.jdog.apirest.domain.model;

public class Score {
    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score CreateScoreByStockSalesWeight(Product product, double stockWeight, double salesWeight) {
        if (salesWeight < 0 || stockWeight < 0 || product == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        CalculateScore score = new CalculateScoreStandard();
        score = new CalculateScoreSalesCriterion(score, salesWeight);
        score = new CalculateScoreStockCriterion(score, stockWeight);

        return new Score(score.calculateScore(product));
    }
    public double getScore() {
        return score;
    }
}
