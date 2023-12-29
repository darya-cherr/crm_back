package com.fitness_project.crm_back.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserSubscription {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.YYYY")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date paymentDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.YYYY")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date expirationDate;

    private Integer amountOfTrainings;

    private Integer remainingTrainings;

    private boolean isActive;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private SubscriptionPlan subscriptionPlan;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserSubscription() {
    }

    public UserSubscription( Date paymentDate, Date expirationDate, Integer amountOfTrainings, Integer remainingTrainings, boolean isActive, SubscriptionPlan subscriptionPlan, User user) {
        this.paymentDate = paymentDate;
        this.expirationDate = expirationDate;
        this.amountOfTrainings = amountOfTrainings;
        this.remainingTrainings = remainingTrainings;
        this.isActive = isActive;
        this.subscriptionPlan = subscriptionPlan;
        this.user = user;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getRemainingTrainings() {
        return remainingTrainings;
    }

    public void setRemainingTrainings(Integer remainingTrainings) {
        this.remainingTrainings = remainingTrainings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAmountOfTrainings() {
        return amountOfTrainings;
    }

    public void setAmountOfTrainings(Integer amountOfTrainings) {
        this.amountOfTrainings = amountOfTrainings;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
