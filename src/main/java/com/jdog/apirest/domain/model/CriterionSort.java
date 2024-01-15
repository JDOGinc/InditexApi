package com.jdog.apirest.domain.model;

public class CriterionSort implements CriterionScore{
    private CriterionScore criterionScore;

    private CriterionSort(CriterionScore criterionScore) {
        this.criterionScore = criterionScore;
    }
    public static CriterionSort createCriterionSortByStockSales(double salesWeight, double stockWeight) {
        CriterionScore score = new CriterionScoreZero();
        score= new CriterionScoreStockCriterion(score, stockWeight);
        score= new CriterionScoreSalesCriterion(score, salesWeight);
        return new CriterionSort(score);
    }

    @Override
    public double calculateScore(Product product) {
      return criterionScore.calculateScore(product);
    }
}
