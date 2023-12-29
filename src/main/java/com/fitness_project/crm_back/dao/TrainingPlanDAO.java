package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.GymExercise;
import com.fitness_project.crm_back.domain.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingPlanDAO  extends JpaRepository<GymExercise, Integer> {
    public List<GymExercise> findByBodyPart(GymExercise.BodyPart bodyPart);
}
