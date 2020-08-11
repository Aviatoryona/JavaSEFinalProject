package dev.yonathaniel.logic;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherLogicI {

    boolean add(Teacher teacher) throws SQLException;
    boolean update(Teacher teacher) throws SQLException;
    boolean delete(Teacher teacher) throws SQLException;
    Teacher find(long id) throws SQLException;
    Teacher find(String staffNo) throws SQLException;
    List<Teacher> findAll() throws SQLException;
}
