package ru.babichev.dao;

import ru.babichev.model.Student;
import java.util.*;

public interface daoInterface {

    List<Student> getAll();

    List<Student> getFiltredByName();

    List<Student> getFiltredBySurname();

    List<Student> getFiltredByPoint();

    Student get(int id);

    Student create(Student student);

    boolean delete(int id);

}
