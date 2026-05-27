package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/stream")
public class StreamController {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final UserService users;
    private final TaskService tasks;

    public StreamController(UserService users, TaskService tasks) {
        this.users = users;
        this.tasks = tasks;
    }

    @GetMapping(value = "/tasks", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(Principal principal) {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.put(principal.getName(), emitter);
        emitter.onCompletion(() -> emitters.remove(principal.getName()));
        emitter.onTimeout(() -> emitters.remove(principal.getName()));
        return emitter;
    }

    // Helper to notify a user; call from elsewhere if you extend service
    public void notifyUser(String username, Object data) {
        SseEmitter e = emitters.get(username);
        if (e != null) {
            try { e.send(SseEmitter.event().name("tasks").data(data)); }
            catch (Exception ex) { emitters.remove(username); }
        }
    }
}
