package dev.yonathaniel.logic;

import dev.yonathaniel.model.Course;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface CourseLogicI {

    boolean add(Course course) throws SQLException;

    boolean update(Course course) throws SQLException;

    boolean delete(Course course) throws SQLException;

    Course find(int id) throws SQLException, ClassNotFoundException;

    List<Course> findAll() throws SQLException, ClassNotFoundException;
}
