package ru.babichev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.babichev.model.Student;

import java.util.*;

@Controller
public class MainController {


    @GetMapping("/")
    public String getAll(Model model) {
        Collection<Student> students = Arrays.asList(
                new Student(1, "Alex", "Brown", 4),
                new Student(2, "Jack", "Smith", 2),
                new Student(3, "Dave", "Johns", 5)
        );
        model.addAttribute("students", students);
        return "index";
    }
}
