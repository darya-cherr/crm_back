package com.fitness_project.crm_back.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date birthDate;

    private String phone;

    private boolean needsTrainer;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer height;

    private Integer weight;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "purpose_id")
    private FitnessPurpose purpose;

    public UserInfo(){}

    public UserInfo(Date birthDate, String phone, boolean needsTrainer, Gender gender, Integer height, Integer weight) {
        this.birthDate = birthDate;
        this.phone = phone;
        this.needsTrainer = needsTrainer;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNeedsTrainer() {
        return needsTrainer;
    }

    public void setNeedsTrainer(boolean needsTrainer) {
        this.needsTrainer = needsTrainer;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public FitnessPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(FitnessPurpose purpose) {
        this.purpose = purpose;
    }

    public static enum Gender {
        male, female;
    }

}
