package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.FoodDairyRecord;
import com.fitness_project.crm_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodDairyDAO extends JpaRepository<FoodDairyRecord, Integer> {
    List<FoodDairyRecord> findByUser(User user);
}
