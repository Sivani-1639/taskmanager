package com.example.taskmanager.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Instant dueDate;

    private Instant createdAt=Instant.now();

    private Instant updatedAt=Instant.now();

    @ManyToOne
    private User owner;

    public Task(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public void setStatus(TaskStatus status){
        this.status=status;
    }

    public Instant getDueDate(){
        return dueDate;
    }

    public void setDueDate(Instant dueDate){
        this.dueDate=dueDate;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt){
        this.createdAt=createdAt;
    }

    public Instant getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt){
        this.updatedAt=updatedAt;
    }

    public User getOwner(){
        return owner;
    }

    public void setOwner(User owner){
        this.owner=owner;
    }
}