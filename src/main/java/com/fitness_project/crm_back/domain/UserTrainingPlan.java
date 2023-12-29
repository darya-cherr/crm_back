package com.fitness_project.crm_back.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserTrainingPlan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<GymExercise> exercises;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserTrainingPlan() {
    }

    public UserTrainingPlan(List<GymExercise> exercises, User user) {
        this.exercises = exercises;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GymExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<GymExercise> exercises) {
        this.exercises = exercises;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
