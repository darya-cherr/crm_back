package com.fitness_project.crm_back.resource.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness_project.crm_back.domain.GymRecordResponse;
import com.fitness_project.crm_back.domain.GymTrainingRecord;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping({"/createGymTraining"})
    public GymTrainingRecord createGymTrainingRecord(@RequestBody Map<String,Object> body) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(body.get("user"), User.class);
        User trainer = mapper.convertValue(body.get("trainer"), User.class);
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("startTime").toString());
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("endTime").toString());
        String text = "";
        if(body.get("description") != null){
            text = body.get("description").toString();
        }
        GymTrainingRecord training = new GymTrainingRecord(startTime, endTime, user,trainer, text, body.get("title").toString());
        return calendarService.createGymTrainingRecord(training);
    }

    @GetMapping({"/getGymTrainings"})
    public List<GymRecordResponse> getGymTrainingRecords() throws ParseException {

        return calendarService.getGymTrainingRecords();
    }

    @PostMapping({"/updateGymTraining/{id}"})
    public GymTrainingRecord updateGymTrainingRecord(@PathVariable String id,@RequestBody Map<String,Object> body) throws ParseException {
        return calendarService.updateGymTrainingRecord(body, id);
    }

}
