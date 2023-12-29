package com.fitness_project.crm_back.resource.rest;

import com.fitness_project.crm_back.dao.RoleDAO;
import com.fitness_project.crm_back.dao.UserDAO;
import com.fitness_project.crm_back.domain.DailyCalorieNormResponse;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserInfo;
import com.fitness_project.crm_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController()
public class UserController {

    @Autowired
    private UserService userService;

   /* @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }*/

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the admin and user";
    }

    @GetMapping({"/user/{id}"})
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping({"/user/change"})
    public User change(@RequestBody Map<String,Object> body){
        return userService.updateProfile(body);
    }

    @GetMapping({"/getAllUsers"})
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping({"/getAllClients"})
    public List<User> getAllClients(){
        return userService.getAllClients();
    }

    @GetMapping({"/getTrainers"})
    public List<User> getGymTrainers() {
        return userService.getTrainers();
    }

    @GetMapping({"/getUserDailyCalorieNorm/{id}"})
    public DailyCalorieNormResponse getUsersDailyCalorieNorm(@PathVariable String id) {
        User user = userService.getUser(id);
        Integer calories, protein, fats, carbohydrates;
        UserInfo info = user.getInfo();
        Date now = new Date();
        long timeBetween = now.getTime() - info.getBirthDate().getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearsBetween);
        if(user.getInfo().getGender() == UserInfo.Gender.male){
            calories = (int) ((int) (88.36 + (13.4 * info.getWeight()) + (4.8 * info.getHeight())) - (5.7 * age));
        }else{
            calories = (int) ((int) (447.6 + (9.2 * info.getWeight()) + (3.1 * info.getHeight())) - (4.3 * age));
        }
        protein = (int) (calories * 0.3 / 4);
        fats = (int) (calories * 0.3 / 9);
        carbohydrates = (int) (calories * 0.4 / 4);

        DailyCalorieNormResponse norm = new DailyCalorieNormResponse(calories, protein, fats, carbohydrates);
        return norm;
    }

}
