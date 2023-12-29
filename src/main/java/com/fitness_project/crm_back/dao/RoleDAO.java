package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.Role;
import com.fitness_project.crm_back.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<Role, Integer> {
    Role findByRoleName(String roleName);

}
