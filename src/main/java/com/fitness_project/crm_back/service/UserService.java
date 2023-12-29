package com.fitness_project.crm_back.service;

import com.fitness_project.crm_back.dao.*;
import com.fitness_project.crm_back.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PurposeDAO purposeDAO;

    @Autowired
    private FoodDairyDAO foodDairyDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String id){
        return userDAO.findUserById(Integer.parseInt(id));
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public List<User> getAllClients(){
        Role role = roleDAO.findById(2).get();
        return userDAO.findUsersByRole(role);
    }

    public List<User> getTrainers(){
        Role role = roleDAO.findById(3).get();
        return userDAO.findUsersByRole(role);
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        roleDAO.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        roleDAO.save(userRole);

        Role userRole2 = new Role();
        userRole2.setRoleName("Gym trainer");
        roleDAO.save(userRole2);

        Role userRole3 = new Role();
        userRole3.setRoleName("Basketball trainer");
        roleDAO.save(userRole3);

        Role userRole4 = new Role();
        userRole4.setRoleName("Volleyball trainer");
        roleDAO.save(userRole4);

        FitnessPurpose fp = new FitnessPurpose();
        fp.setPurposeDescription("Keeping fit");
        purposeDAO.save(fp);

        FitnessPurpose fp2 = new FitnessPurpose();
        fp2.setPurposeDescription("Set of muscle mass");
        purposeDAO.save(fp2);

        FitnessPurpose fp3 = new FitnessPurpose();
        fp3.setPurposeDescription("Weight loss");
        purposeDAO.save(fp3);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserEmail("admin@mail.ru");
        userDAO.save(adminUser);
        adminUser.setRole(adminRole);
        userDAO.save(adminUser);

        User basketUser = new User();
        basketUser.setUserName("Кирилл");
        basketUser.setUserPassword(getEncodedPassword("kirill"));
        basketUser.setUserEmail("kirill@mail.ru");
        userDAO.save(basketUser);
        basketUser.setRole(userRole3);
        userDAO.save(basketUser);

        User gt = new User();
        gt.setUserName("Артем");
        gt.setUserPassword(getEncodedPassword("артем"));
        gt.setUserEmail("артем@mail.ru");
        userDAO.save(gt);
        gt.setRole(userRole2);
        userDAO.save(gt);

        User gt1 = new User();
        gt1.setUserName("Андрей");
        gt1.setUserPassword(getEncodedPassword("андрей"));
        gt1.setUserEmail("андрей@mail.ru");
        userDAO.save(gt1);
        gt1.setRole(userRole2);
        userDAO.save(gt1);

        User gt2 = new User();
        gt2.setUserName("Евгений");
        gt2.setUserPassword(getEncodedPassword("евгений"));
        gt2.setUserEmail("евгений@mail.ru");
        userDAO.save(gt2);
        gt2.setRole(userRole2);
        userDAO.save(gt2);
    }


    public User registerNewUser(User user, String role) {
        Role userRole = roleDAO.findByRoleName(role);
        user.setRole(userRole);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDAO.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User
    completeRegister(UserInfo userInfo, String purpose, Integer userId){
        User user = userDAO.findById(userId).get();
        FitnessPurpose fitnessPurpose = purposeDAO.findByPurposeDescription(purpose);
        userInfo.setPurpose(fitnessPurpose);
        user.setInfo(userInfo);
        return userDAO.save(user);
    }

    public FoodDairyRecord addFoodDairyRecord(FoodDairyRecord foodDairyRecord) throws ParseException {

        return foodDairyDAO.save(foodDairyRecord);
    }

    public FoodDairyRecord getFoodDairyRecord() {
        return foodDairyDAO.findById(1).get();
    }

    public List<FoodDairyRecord> getAllUsersFoodDairyRecords(User user) {
        return foodDairyDAO.findByUser(user);
    }

    public FoodDairyRecord updateFoodDairyRecord(Map<String,Object> body, String id) throws ParseException {
        FoodDairyRecord record = foodDairyDAO.findById(Integer.parseInt(id)).get();
        record.setRecordDate(new SimpleDateFormat("dd.MM.yyyy").parse(body.get("dueDate").toString()));
        record.setCalories(Integer.parseInt(body.get("calories").toString()));
        record.setFats(Integer.parseInt(body.get("fats").toString()));
        record.setProtein(Integer.parseInt(body.get("protein").toString()));
        record.setCarbohydrates(Integer.parseInt(body.get("carbohydrates").toString()));
        record.setBreakfast(body.get("breakfast").toString());
        record.setLunch(body.get("lunch").toString());
        record.setDinner(body.get("dinner").toString());
        return foodDairyDAO.save(record);
    }

    public User updateProfile(Map<String, Object> body) {
        User user = userDAO.findUserById(Integer.parseInt(body.get("id").toString()));
        user.setUserEmail(body.get("email").toString());
        user.setUserName(body.get("userName").toString());
        return userDAO.save(user);
    }
}
