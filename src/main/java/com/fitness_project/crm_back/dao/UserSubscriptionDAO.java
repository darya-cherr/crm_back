package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionDAO extends JpaRepository<UserSubscription, Integer> {
    List<UserSubscription> findByUser(User user);


}
