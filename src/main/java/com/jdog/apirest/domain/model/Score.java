package com.jdog.apirest.domain.model;

public class Score {
    private double score;

    private Score() {
    }

    private Score(double score) {
        this.score = score;
    }

    public static Score create(double score) {
        return new Score(score);
    }
    public static Score createWithDefaultScore() {
        double score = 0;
        return new Score(score);
    }

    public static Score CreateScoreByStockSalesWeight(Product product, double stockWeight, double salesWeight) {
        if (salesWeight < 0 || stockWeight < 0 || product == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        CalculateScore calculateScore = new CalculateScoreStandard();
        calculateScore = new CalculateScoreSalesCriterion(calculateScore, salesWeight);
        calculateScore = new CalculateScoreStockCriterion(calculateScore, stockWeight);
        double score = calculateScore.calculateScore(product);

        return new Score(score);
    }



    public double getScore() {
        return score;
    }
}
