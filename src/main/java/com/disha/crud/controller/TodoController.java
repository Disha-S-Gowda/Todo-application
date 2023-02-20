package com.disha.crud.controller;

import com.disha.crud.exception.ResourceNotFoundException;
import com.disha.crud.model.TodoItem;
import com.disha.crud.repository.TodoRepository;
import com.disha.crud.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("test")
    public String test(){
        System.out.println(" api's executing...");
        return "Sucess";
    }
    @GetMapping("/to_do")
    public List<TodoItem> retrieveAllTodo(){
        System.out.println(" retrieve all executing...");
        return todoRepository.findAll();
    }

    @PostMapping("/to_do")
    public TodoItem createTodo(@Valid @RequestBody TodoItem todoItem){

        System.out.println(" create todo executing...");
        todoItem.setId(sequenceGeneratorService.generateSequence(TodoItem.SEQUENCE_NAME));
        return todoRepository.save(todoItem);
    }

    @PutMapping("/to_do/{id}")
    public ResponseEntity<TodoItem>  updateTodoItem(@PathVariable(value = "id") Long itemID,
                                                   @Valid @RequestBody TodoItem todoItem) throws ResourceNotFoundException {

        System.out.println(" update todo executing...");
        TodoItem item = todoRepository.findById(itemID).orElseThrow(() -> new ResourceNotFoundException(" Todo item not found for this id :: " + itemID));

        item.setDescription(todoItem.getDescription());
        item.setStatus(todoItem.isStatus());
        final TodoItem updatedItem = todoRepository.save(item);

        System.out.println("Status = " + todoItem.isStatus());

         return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/to_do/{id}")
    public Map<String, Boolean> deleteTodoItem(@PathVariable(value = "id") Long itemID)
    throws ResourceNotFoundException{

        System.out.println(" update todo executing...");
        TodoItem todoItem = todoRepository.findById(itemID)
                .orElseThrow(() -> new ResourceNotFoundException(" Todo item not found for this id :: " + itemID));

        todoRepository.delete(todoItem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  response;
    }

}
