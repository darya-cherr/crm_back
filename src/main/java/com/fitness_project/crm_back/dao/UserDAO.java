package com.fitness_project.crm_back.dao;

import com.fitness_project.crm_back.domain.Role;
import com.fitness_project.crm_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
    User findUserById(Integer id);
    List<User> findUsersByRole(Role role);
}
