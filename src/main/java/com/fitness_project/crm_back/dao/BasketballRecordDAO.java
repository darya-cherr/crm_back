package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.BasketballRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketballRecordDAO extends JpaRepository<BasketballRecord, Integer> {

}
