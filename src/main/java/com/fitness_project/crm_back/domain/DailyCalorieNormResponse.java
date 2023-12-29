package com.fitness_project.crm_back.domain;

public class DailyCalorieNormResponse {
    private Integer calories;
    private Integer protein;
    private Integer fats;
    private Integer carbohydrates;

    public DailyCalorieNormResponse(Integer calories, Integer protein, Integer fats, Integer carbohydrates) {
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
