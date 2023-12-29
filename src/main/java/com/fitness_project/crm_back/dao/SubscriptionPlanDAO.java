package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanDAO extends JpaRepository<SubscriptionPlan, Integer> {

}
