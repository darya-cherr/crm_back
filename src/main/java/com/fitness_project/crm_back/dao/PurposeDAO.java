package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.FitnessPurpose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurposeDAO extends CrudRepository<FitnessPurpose, Integer> {
    FitnessPurpose findByPurposeDescription(String purposeDescription);
}
