package com.fitness_project.crm_back.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness_project.crm_back.dao.BasketballRecordDAO;
import com.fitness_project.crm_back.dao.GymTrainingsDAO;
import com.fitness_project.crm_back.dao.UserDAO;
import com.fitness_project.crm_back.domain.BasketballRecord;
import com.fitness_project.crm_back.domain.GymRecordResponse;
import com.fitness_project.crm_back.domain.GymTrainingRecord;
import com.fitness_project.crm_back.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CalendarService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GymTrainingsDAO gymTrainingsDAO;
    @Autowired
    private BasketballRecordDAO basketballRecordDAO;

    public GymTrainingRecord createGymTrainingRecord(GymTrainingRecord training){
        return gymTrainingsDAO.save(training);
    }

    public BasketballRecord createBasketballRecord(BasketballRecord training){
        return basketballRecordDAO.save(training);
    }

    public GymTrainingRecord updateGymTrainingRecord(Map<String,Object> body, String id) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        GymTrainingRecord record = gymTrainingsDAO.findById(Integer.parseInt(id)).get();
        record.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("startTime").toString()));
        record.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("endTime").toString()));
        record.setTrainer(mapper.convertValue(body.get("trainer"), User.class));
        String text = "";
        if(body.get("description") != null){
            text = body.get("description").toString();
        }
        record.setDescription(text);
        return gymTrainingsDAO.save(record);
    }

    public List<GymRecordResponse> getGymTrainingRecords() throws ParseException {
        List<GymTrainingRecord> records = gymTrainingsDAO.findAll();
        List<GymRecordResponse> newRecords = new ArrayList<>();
        for (GymTrainingRecord record: records) {
            newRecords.add(new GymRecordResponse(record.getId(),record.getStartTime(), record.getEndTime(), record.getUser(), record.getTrainer(), record.getDescription(), record.getTitle()));
        }
        return newRecords;
    }

    public List<BasketballRecord> getBasketballRecords() throws ParseException {
        List<BasketballRecord> records = basketballRecordDAO.findAll();
        List<BasketballRecord> newRecords = new ArrayList<>();
//        for (BasketballRecord record: records) {
//            newRecords.add(new GymRecordResponse(record.getId(),record.getStartTime(), record.getEndTime(), record.getUser(), record.getCompanyName(), record.getAmountOfPeople());
//        }
        return newRecords;
    }
}
