package com.example.taskmanager.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable=false)
    private String username;

    @Column(nullable=false)
    private String passwordHash;

    private String role="USER";

    @OneToMany(mappedBy="owner")
    private Set<Task> tasks;

    public User(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash=passwordHash;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role=role;
    }

    public Set<Task> getTasks(){
        return tasks;
    }

    public void setTasks(Set<Task> tasks){
        this.tasks=tasks;
    }
}