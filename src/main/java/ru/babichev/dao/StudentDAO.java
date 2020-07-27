package ru.babichev.dao;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.babichev.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class StudentDAO implements daoInterface {


    private static final RowMapper<Student> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Student.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertStudent;

    public StudentDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertStudent = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("students")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM students ORDER BY id ASC", ROW_MAPPER);
    }

    public List<Student> getFiltredByPoint() {
        List<Student> list = jdbcTemplate.query(
                "SELECT * FROM students",
                ROW_MAPPER);
        list.sort((x, y) -> {
            return x.getPoint() > y.getPoint()? 1 : -1;
        });

        return list;
    }

    public Student get(int id) {
        List<Student> student = jdbcTemplate.query(
                "SELECT * FROM students WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(student);
    }

    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM students WHERE id = ?", id) != 1;
    }

    @Override
    public Student create(Student student) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", student.getId())
                .addValue("name", student.getName())
                .addValue("surname", student.getSurname())
                .addValue("point", student.getPoint());

        if (student.isNew()) {
            Number newId = insertStudent.executeAndReturnKey(map);
            student.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE students " +
                    "   SET name=:name, surname=:surname, point=:point " +
                    " WHERE id=:id", map) == 0) {
                return null;
            }
        }
        return student;
    }

}
