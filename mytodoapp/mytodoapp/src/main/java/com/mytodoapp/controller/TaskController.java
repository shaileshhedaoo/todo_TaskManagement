package com.mytodoapp.controller;
import com.mytodoapp.dto.Request.TaskRequest;
import com.mytodoapp.dto.Response.TaskResponse;
import com.mytodoapp.model.Task;
import com.mytodoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        TaskResponse taskResponse= taskService.save(taskRequest);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@RequestParam Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<TaskResponse> searchTrainees(@RequestParam("title") String title){
        return taskService.searchTaskByName(title);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskService.updateTask(id, taskDetails);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task Deleted successfully",HttpStatus.NO_CONTENT);
    }


}

