package com.disha.crud.repository;

import com.disha.crud.model.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<TodoItem, Long> {

}
