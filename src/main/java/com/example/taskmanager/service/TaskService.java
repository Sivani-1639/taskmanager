package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo){
        this.repo=repo;
    }

    public List<Task> getAll(){
        return repo.findAll();
    }

    public Task create(Task task){
        return repo.save(task);
    }

    public Task update(Long id,Task updated){

        Task task=repo.findById(id)
                .orElseThrow();

        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setStatus(updated.getStatus());
        task.setDueDate(updated.getDueDate());

        return repo.save(task);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}