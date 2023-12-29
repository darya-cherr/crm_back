package com.fitness_project.crm_back.resource.rest;

import com.fitness_project.crm_back.domain.FoodDairyRecord;
import com.fitness_project.crm_back.domain.GymRecordResponse;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foodDairy")
public class FoodDairyController {

    @Autowired
    private UserService userService;
    @PostMapping({"/createRecord"})
    public FoodDairyRecord createFoodDairyRecord(@RequestBody Map<String,Object> body) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(body.get("dueDate").toString());
        User user = userService.getUser(body.get("userId").toString());
        FoodDairyRecord foodDairyRecord = new FoodDairyRecord(date, body.get("breakfast").toString(), body.get("lunch").toString(), body.get("dinner").toString(), Integer.parseInt(body.get("calories").toString()), Integer.parseInt(body.get("protein").toString()), Integer.parseInt(body.get("fats").toString()), Integer.parseInt(body.get("carbohydrates").toString()), user);
        return userService.addFoodDairyRecord(foodDairyRecord);
    }

    @PostMapping({"/updateRecord/{id}"})
    public FoodDairyRecord getFoodDairyRecord(@PathVariable String id, @RequestBody Map<String,Object> body) throws ParseException {
        return userService.updateFoodDairyRecord(body, id);
    }

    @GetMapping({"/getAllRecords/{id}"})
    public List<FoodDairyRecord> getGymTrainingRecords(@PathVariable String id) throws ParseException {
        User user = userService.getUser(id);
        Calendar cal = Calendar.getInstance();
        List<FoodDairyRecord> list = userService.getAllUsersFoodDairyRecords(user);
        for (FoodDairyRecord record: list) {
            cal.setTime(record.getRecordDate());
            cal.add(Calendar.DATE,1);
            record.setRecordDate(cal.getTime());
        }
        return list;
    }
}
