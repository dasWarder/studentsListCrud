package ru.babichev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.babichev.dao.StudentDAO;
import ru.babichev.dao.daoInterface;
import ru.babichev.model.Student;
import ru.babichev.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class MainController {


//    private daoInterface studentDAO;
    private StudentService studentService;

    public MainController(StudentService studentService) {
//        this.studentDAO = studentDAO;
        this.studentService = studentService;
    }


    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "index";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("student", studentService.get(getIntParam(request, "id")));
        return "create";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student("", "", 4));
        return "create";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int id = getIntParam(request, "id");
        studentService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/by")
    public String getAllBetween(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "surname", required = false) String surname,
                                @RequestParam(name = "point", required = false) String point,
                                Model model) {
        if (name != null) {
            model.addAttribute("students",
                    studentService.getFiltredByParam("name"));
        } else if (surname != null) {
            model.addAttribute("students",
                    studentService.getFiltredByParam("surname"));
        } else if (point != null) {
            model.addAttribute("students",
                    studentService.getFiltredByParam("point"));
        }

        return "index";
    }

    @PostMapping("/create")
    public String Create(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int point = getIntParam(request, "point");

        Student student1 = new Student(name, surname, point);

        studentService.create(student1);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam(name = "id", required = false) int id,
                                 HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int point = getIntParam(request, "point");

        Student student1 = new Student(name, surname, point);

        studentService.update(student1, id);

        return "redirect:/";
    }


    private int getIntParam(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

}
