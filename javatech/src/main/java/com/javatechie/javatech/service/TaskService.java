package com.javatechie.javatech.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.javatech.model.Task;
import com.javatechie.javatech.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(String taskId) {
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity) {
        return repository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee) {
        return repository.getTasksByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest) {
        // get the existing document from DB.
        // populate new value from request to existing object/entity/document.
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();

        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        return repository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        repository.deleteById(taskId);
        return taskId + " task deleted from dashboard.";
    }
}
