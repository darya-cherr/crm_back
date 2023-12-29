package com.fitness_project.crm_back.domain;

import java.util.Date;

public class GymRecordResponse {
    private Integer id;
    private Date start;
    private Date end;
    private User user;
    private User trainer;
    private String description;
    private String title;


    public GymRecordResponse(Integer id, Date start, Date end, User user, User trainer, String description, String title) {
        this.id = id;
        this.title = title;
        if(trainer != null){
            this.title = title + ",\n Тренер: " + trainer.getUserName();
        }
        this.start = start;
        this.end = end;
        this.user = user;
        this.trainer = trainer;
        this.description = description;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }
}
