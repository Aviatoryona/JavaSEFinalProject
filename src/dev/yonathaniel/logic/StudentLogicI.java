package dev.yonathaniel.logic;


import dev.yonathaniel.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentLogicI {
    boolean add(Student student) throws SQLException;
    boolean update(Student student) throws SQLException;
    boolean delete(Student student) throws SQLException;
    Student find(long id) throws SQLException;
    Student find(String registrationNo) throws SQLException;
    List<Student> findAll() throws SQLException;
}
