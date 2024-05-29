package com.mytodoapp.service;

import com.mytodoapp.dto.Request.TaskRequest;
import com.mytodoapp.dto.Response.TaskResponse;
import com.mytodoapp.exception.TaskNotFoundException;
import com.mytodoapp.model.Task;
import com.mytodoapp.repository.TaskRepository;
import com.mytodoapp.transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.Transform;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
      this.taskRepository=taskRepository;
    }

    public TaskResponse save(TaskRequest taskRequest) {
        Task task= TaskTransformer.taskRequestToTask(taskRequest);
        // save the customer
         Task saveTask= taskRepository.save(task);
        // entity -> response dto
         return  TaskTransformer.taskToTaskResponse(task);
    }


    public List<TaskResponse> findAll() {
        List<Task> tasks =   taskRepository.findAll();
        List<TaskResponse> TaskResponses = new ArrayList<>();
        for(Task task: tasks) {
            TaskResponses.add(TaskTransformer.taskToTaskResponse(task));
        }
        return TaskResponses;
    }

    public TaskResponse getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(TaskTransformer::taskToTaskResponse)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    public List<TaskResponse> searchTaskByName(String title) {

        List<Task> tasks = taskRepository.findByTitleContaining(title);
        return tasks.stream()
                .map(TaskTransformer::taskToTaskResponse1)
                .collect(Collectors.toList());

    }

}

