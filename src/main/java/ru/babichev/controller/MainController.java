package ru.babichev.controller;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.babichev.dao.StudentDAO;
import ru.babichev.dao.daoInterface;
import ru.babichev.model.Student;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("student", studentDAO.get(getIntParam(request, "id")));
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
        studentDAO.delete(id);
        return "redirect:/";
    }

    @GetMapping("/by")
    public String getAllBetween(Model model) {
        model.addAttribute("students", studentDAO.getFiltredByPoint());
        return "index";
    }

    @PostMapping("/create")
    public String updateOrCreate(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int point = getIntParam(request, "point");

        Student student1 = new Student(name, surname, point);

        studentDAO.create(student1);
        return "redirect:/";
    }


    private int getIntParam(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

}
