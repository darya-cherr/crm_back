package com.fitness_project.crm_back.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String subtitle;
    private Integer monthlyPrice;

    @Column(columnDefinition = "text[]")
    @Type(type = "com.fitness_project.crm_back.util.GenericArrayUserType")
    private String[] planBenefits;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(String title, String subtitle, Integer monthlyPrice, String[] planBenefits) {
        this.title = title;
        this.subtitle = subtitle;
        this.monthlyPrice = monthlyPrice;
        this.planBenefits = planBenefits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Integer monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String[] getPlanBenefits() {
        return planBenefits;
    }

    public void setPlanBenefits(String[] planBenefits) {
        this.planBenefits = planBenefits;
    }
}
