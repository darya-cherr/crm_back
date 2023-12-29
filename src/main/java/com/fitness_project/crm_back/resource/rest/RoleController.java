package com.fitness_project.crm_back.resource.rest;

import com.fitness_project.crm_back.domain.FitnessPurpose;
import com.fitness_project.crm_back.domain.Role;
import com.fitness_project.crm_back.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
    /*@PostMapping({"/createNewPurpose"})
    public FitnessPurpose createNewPurpose(@RequestBody FitnessPurpose fitnessPurpose){
        return roleService.createNewRole(fitnessPurpose);
    }*/

}
