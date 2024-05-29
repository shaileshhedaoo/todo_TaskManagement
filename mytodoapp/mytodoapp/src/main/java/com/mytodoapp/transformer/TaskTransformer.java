package com.mytodoapp.transformer;

import com.mytodoapp.dto.Request.TaskRequest;
import com.mytodoapp.dto.Response.TaskResponse;
import com.mytodoapp.model.Task;

public class TaskTransformer {

    public  static Task taskRequestToTask(TaskRequest taskRequest)
    {
        return  Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .completed(taskRequest.isCompleted())
                .build();
    }

   public static TaskResponse taskToTaskResponse(Task task)
   {
        return  TaskResponse.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
   }


    public static TaskResponse taskToTaskResponse1(Task task) {
        TaskResponse response = new TaskResponse();
        response.setDescription(task.getDescription());
        response.setTitle(task.getTitle());
        return response;
    }
}
