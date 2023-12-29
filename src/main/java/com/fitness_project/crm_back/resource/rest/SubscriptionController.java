package com.fitness_project.crm_back.resource.rest;

import com.fitness_project.crm_back.configuration.StripeClient;
import com.fitness_project.crm_back.domain.Role;
import com.fitness_project.crm_back.domain.SubscriptionPlan;
import com.fitness_project.crm_back.domain.UserSubscription;
import com.fitness_project.crm_back.service.SubscriptionService;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {


    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping({"/createNewSubscriptionPlan"})
    public SubscriptionPlan createSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan){
        return subscriptionService.createNewSubscriptionPlan(subscriptionPlan);
    }

    @GetMapping({"/getAllSubscriptionPlans"})
    public List<SubscriptionPlan> getAllSubscriptionPlans(){
        return subscriptionService.getAllSubscriptionPlans();
    }

    @GetMapping({"/getAllUserSubscriptionPlans/{id}"})
    public List<UserSubscription> getAllUserSubscriptionPlans(@PathVariable String id){
        return subscriptionService.getAllUserSubscriptionPlans(Integer.parseInt(id));
    }

    @GetMapping({"/getUserSubscriptionPlans/{id}"})
    public UserSubscription getUserSubscriptionPlans(@PathVariable String id){
        return subscriptionService.getUserSubscriptionPlans(Integer.parseInt(id));
    }

    @PostMapping({"/createTransaction"})
    public UserSubscription createTransaction(@RequestBody Map<String,Object> body) throws Exception {
        return subscriptionService.createTransaction(body);
    }
}
