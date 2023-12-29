package com.fitness_project.crm_back.service;

import com.fitness_project.crm_back.dao.PurposeDAO;
import com.fitness_project.crm_back.dao.RoleDAO;
import com.fitness_project.crm_back.domain.FitnessPurpose;
import com.fitness_project.crm_back.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PurposeDAO purposeDAO;
    public Role createNewRole(Role role){
        return roleDAO.save(role);
    }

    /*public FitnessPurpose createNewRole(FitnessPurpose purpose){
        return purposeDAO.save(purpose);
    }*/
}
