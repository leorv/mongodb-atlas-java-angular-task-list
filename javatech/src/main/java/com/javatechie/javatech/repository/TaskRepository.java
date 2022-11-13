package com.javatechie.javatech.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.javatechie.javatech.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findBySeverity(int severity);

    /* Dica:
    * @Query("{assignee: ?0, fieldName: ?1 }") <== Caso tivéssemos mais de um argumento.
    */
    @Query("{assignee: ?0}")
    List<Task> getTasksByAssignee(String assignee);
}
