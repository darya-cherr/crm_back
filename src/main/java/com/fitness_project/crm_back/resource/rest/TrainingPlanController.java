package com.fitness_project.crm_back.resource.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness_project.crm_back.dao.TrainingPlanDAO;
import com.fitness_project.crm_back.dao.UserDAO;
import com.fitness_project.crm_back.dao.UserTrainingPlanDAO;
import com.fitness_project.crm_back.domain.GymExercise;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserInfo;
import com.fitness_project.crm_back.domain.UserTrainingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/trainingPlan")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanDAO trainingPlanDAO;
    @Autowired
    private UserTrainingPlanDAO userTrainingPlanDAO;
    @Autowired
    private UserDAO userDAO;

    @PostMapping(value="/addUsersTrainingPlan")
    public UserTrainingPlan addUsersTrainingPlan(@RequestBody Map<String,Object> body){
        ObjectMapper mapper = new ObjectMapper();
        List<GymExercise> exercises = (mapper.convertValue(body.get("exercises"), new TypeReference<List<GymExercise>>() {}));
        User user = userDAO.findById(Integer.parseInt(body.get("userId").toString())).get();
        return userTrainingPlanDAO.save(new UserTrainingPlan(exercises, user));
    }

    @GetMapping(value="/getUsersTrainingPlan/{id}")
    public List<List<GymExercise>> getUsersTrainingPlan(@PathVariable String id){
        User user = userDAO.findUserById(Integer.parseInt(id));
        try{
        UserTrainingPlan trainingPlan = userTrainingPlanDAO.findByUser(user);
        List<List<GymExercise>> exercises = new ArrayList<>();
        List<GymExercise> back = new ArrayList<>();
        List<GymExercise> arms = new ArrayList<>();
        List<GymExercise> buttocks = new ArrayList<>();
        List<GymExercise> legs = new ArrayList<>();
        List<GymExercise> shoulders = new ArrayList<>();
        List<GymExercise> press = new ArrayList<>();
        List<GymExercise> chest = new ArrayList<>();
        for (GymExercise exercise : trainingPlan.getExercises()) {
            switch (exercise.getBodyPart()){
                case  back:
                    back.add(exercise);
                    break;
                case  arms:
                    arms.add(exercise);
                    break;
                case legs:
                    legs.add(exercise);
                    break;
                case  chest:
                    chest.add(exercise);
                    break;
                case buttocks:
                    buttocks.add(exercise);
                    break;
                case  press:
                    press.add(exercise);
                    break;
                case  shoulders:
                    shoulders.add(exercise);
                    break;
            }
        }
        exercises.add(back);
        exercises.add(arms);
        exercises.add(legs);
        exercises.add(shoulders);
        exercises.add(buttocks);
        exercises.add(chest);
        exercises.add(press);
        return exercises;
        }
        catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/addExercise")
    public GymExercise addNewExercise(@RequestBody Map<String,Object> body) throws ParseException {
        return trainingPlanDAO.save(new GymExercise(body.get("exerciseName").toString(),  body.get("exerciseDescription").toString(), GymExercise.BodyPart.valueOf(body.get("bodyPart").toString())));
    }

    @GetMapping(value = "/getBackExercises")
    public List<GymExercise> getBackExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.back);
    }

    @GetMapping(value = "/getArmsExercises")
    public List<GymExercise> getArmsExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.arms);
    }

    @GetMapping(value = "/getShouldersExercises")
    public List<GymExercise> getShouldersExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.shoulders);
    }
    @GetMapping(value = "/getLegsExercises")
    public List<GymExercise> getLegsExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.legs);
    }
    @GetMapping(value = "/getButtocksExercises")
    public List<GymExercise> getButtocksExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.buttocks);
    }
    @GetMapping(value = "/getChestExercises")
    public List<GymExercise> getChestExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.chest);
    }
    @GetMapping(value = "/getPressExercises")
    public List<GymExercise> getPressExercises(){
        return trainingPlanDAO.findByBodyPart(GymExercise.BodyPart.press);
    }

}
