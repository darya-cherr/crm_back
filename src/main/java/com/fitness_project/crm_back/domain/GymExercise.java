package com.fitness_project.crm_back.domain;

import javax.persistence.*;

@Entity
public class GymExercise {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String exerciseName;
    @Column(columnDefinition = "text")
    private String exerciseDescription;

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;


    public static enum BodyPart {
        back, arms, shoulders,chest, legs, buttocks, press,
    }

    public GymExercise() {
    }

    public GymExercise(String exerciseName, String exerciseDescription, BodyPart bodyPart) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.bodyPart = bodyPart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }
}
