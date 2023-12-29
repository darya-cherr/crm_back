package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserTrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTrainingPlanDAO  extends JpaRepository<UserTrainingPlan,Integer> {
    UserTrainingPlan findByUser(User user);
}
