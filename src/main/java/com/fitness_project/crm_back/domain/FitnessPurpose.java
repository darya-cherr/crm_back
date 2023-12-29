package com.fitness_project.crm_back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FitnessPurpose {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String purposeDescription;

    public Integer getId() {
        return id;
    }

    public String getPurposeDescription() {
        return purposeDescription;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }
}
