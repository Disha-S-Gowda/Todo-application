package com.disha.mywebapp;

import com.disha.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private TodoRepository todoRepository;
    @RequestMapping("home")
    public Student home(){
        return new Student("Ram","Nandan");
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Ramesh","Tripathi"));
        students.add(new Student("Sahana","Tripathi"));
        students.add(new Student("Tanya","Tripathi"));
        students.add(new Student("Raj","Tripathi"));

        return students;
    }

    @GetMapping("student/{firstname}/{lastname}")
    public Student getStudentPathVariable(
            @PathVariable("firstname") String firstName,
            @PathVariable("lastname") String lastName
    ){
        return new Student(firstName,lastName);
    }

    @GetMapping("student")
    public Student getStudentQueryParam(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName
    ){
        return new Student(firstName,lastName);
    }
}
