package ru.babichev.controller;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.babichev.model.Student;

import java.util.*;

@Controller
public class MainController {
    private Integer id = 0;

    private Integer incrementId() {
        return id += 1;
    }

    private List<Student> students = new ArrayList<>();


    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/showCreatePage")
    public String showPage() {
        return "create";
    }

    @PostMapping("/create")
    public String addStudent(@ModelAttribute Student student) {
        student.setId(incrementId());
        students.add(student);
        return "redirect:/";
    }

}
