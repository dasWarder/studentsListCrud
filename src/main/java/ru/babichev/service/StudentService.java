package ru.babichev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.babichev.dao.StudentDAO;
import ru.babichev.model.Student;
import ru.babichev.utils.exception.NotFoundException;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public List<Student> getFiltredByParam(String param) {
        List<Student> students = null;
        switch (param) {
            case "name":
                students = studentDAO.getFiltredByName();
                break;
            case "surname":
                students = studentDAO.getFiltredBySurname();
                break;
            case "point":
                students = studentDAO.getFiltredByPoint();
                break;
            default: throw new NotFoundException("There is no this filter");
        }

        return students;
    }

    public Student get(int id){
        return studentDAO.get(id);
    }

    public Student create(Student student){
        return studentDAO.create(student);
    }

    public Student update(Student student, int id){
        if (student.isNew()) {
            student.setId(id);
        }
        return studentDAO.create(student);
    }

    public void delete(int id) {
        studentDAO.delete(id);
    }
}
