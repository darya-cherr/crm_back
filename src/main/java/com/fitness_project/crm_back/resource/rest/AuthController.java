package com.fitness_project.crm_back.resource.rest;

import com.fitness_project.crm_back.domain.JwtRequest;
import com.fitness_project.crm_back.domain.JwtResponse;
import com.fitness_project.crm_back.domain.User;
import com.fitness_project.crm_back.domain.UserInfo;
import com.fitness_project.crm_back.service.JwtService;
import com.fitness_project.crm_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping(value ="register")
    public JwtResponse registerNewUser(@RequestBody Map<String,Object> body) throws Exception {
        User user = new User(body.get("userName").toString(), body.get("userEmail").toString(), body.get("userPassword").toString());
        userService.registerNewUser(user,  body.get("role").toString());
        JwtRequest jwtRequest = new JwtRequest(user.getUserEmail(), body.get("userPassword").toString());
        return jwtService.createJwtToken(jwtRequest);
    }

    @PostMapping(value ="registerByAdmin")
    public User registerByAdmin(@RequestBody Map<String,Object> body) throws Exception {
        User user = new User(body.get("userName").toString(), body.get("userEmail").toString(), body.get("userPassword").toString());
        return userService.registerNewUser(user,  body.get("role").toString());
    }


    @PostMapping(value = "authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }



    @PreAuthorize("hasRole('User')")
    @PostMapping(value = "/completeRegister")
    public User completeRegister(@RequestBody Map<String,Object> body) throws ParseException {
        Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(body.get("birthDate").toString());
        UserInfo userInfo = new UserInfo(birthDate, body.get("phone").toString(), (Boolean) body.get("trainer"), UserInfo.Gender.valueOf(body.get("gender").toString()),Integer.valueOf((String) body.get("height")), Integer.valueOf((String)  body.get("weight")));
        return userService.completeRegister(userInfo, body.get("userPurpose").toString(), (Integer) body.get("userId"));
    }



}
