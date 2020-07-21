package ru.babichev.controller;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.babichev.dao.StudentDAO;
import ru.babichev.dao.daoInterface;
import ru.babichev.model.Student;

import java.util.*;

@Controller
public class MainController {


    private daoInterface studentDAO;

    public MainController(daoInterface studentDAO) {
        this.studentDAO = studentDAO;
    }


    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("students", studentDAO.getAll());
        return "index";
    }

    @GetMapping("/showCreatePage")
    public String showPage() {
        return "create";
    }

    @GetMapping("/getSortedByPoint")
    public String getAllSortedByPoint(Model model) {
        model.addAttribute("students", studentDAO.getFiltredByPoint());
        return "index";
    }

    @PostMapping("/create")
    public String addStudent(@ModelAttribute Student student) {

        return "redirect:/";
    }

}
