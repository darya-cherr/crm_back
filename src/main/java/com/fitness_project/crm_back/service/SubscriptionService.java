package com.fitness_project.crm_back.service;

import com.fitness_project.crm_back.dao.SubscriptionPlanDAO;
import com.fitness_project.crm_back.dao.UserDAO;
import com.fitness_project.crm_back.dao.UserSubscriptionDAO;
import com.fitness_project.crm_back.domain.SubscriptionPlan;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {

    private static final String KEY="pk_test_51N7eazKcFkG6jPBRObAZKIEnwT9KE7WcizQD3r1hRJi3lHAc19DHbZyEttBlFNAWM5fYMfSjesSk2vEcHFxxbFxi00VsqT5rLw";
    private static final String KEY_SECRET="sk_test_51N7eazKcFkG6jPBRBpZOjvgvz8Q5F3AYWZ2wx7MIjFvAoT55jKiiAMW99DE8MVZpShaZWq7yzFHgR7XiIq91ZoqV000b32VFdZ";
    private static final String CURRENCY="BYN";
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserSubscriptionDAO userSubscriptionDAO;
    @Autowired
    private SubscriptionPlanDAO subscriptionPlanDAO;


    public SubscriptionPlan createNewSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanDAO.save(subscriptionPlan);
    }

    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanDAO.findAll();
    }

    public UserSubscription createTransaction(Map<String,Object> body) {
        User user = userDAO.findUserById(Integer.parseInt(body.get("userId").toString()));
        SubscriptionPlan plan = subscriptionPlanDAO.findById(Integer.parseInt(body.get("plan").toString())).get();
        Calendar cal = Calendar.getInstance();
        Date paymentDate = new Date();
        cal.setTime(paymentDate);
        cal.add(Calendar.DATE,30);
        Date expirationDate = cal.getTime();
        UserSubscription userSubscription = new UserSubscription(paymentDate, expirationDate, Integer.parseInt(body.get("amountOfTrainings").toString()), Integer.parseInt(body.get("amountOfTrainings").toString()), true,plan, user);
        return userSubscriptionDAO.save(userSubscription);
    }


    public UserSubscription getUserSubscriptionPlans(int id) {
        User user = userDAO.findUserById(id);
        List<UserSubscription> subscriptions = userSubscriptionDAO.findByUser(user);
        for (UserSubscription subs: subscriptions) {
            if(subs.isActive()){
                return subs;
            }
        }

        return null;
    }

    public List<UserSubscription> getAllUserSubscriptionPlans(Integer id){
        User user = userDAO.findUserById(id);
        return userSubscriptionDAO.findByUser(user);
    }
}
