package dev.yonathaniel.logic;

import dev.yonathaniel.db.DbConnection;
import dev.yonathaniel.db.DbConnectionI;
import dev.yonathaniel.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherLogic implements TeacherLogicI {
    /**
     *
     */
    private DbConnectionI dbConnectionI;

    public TeacherLogic() throws SQLException, ClassNotFoundException {
        this.dbConnectionI = new DbConnection();
    }

    @Override
    public boolean add(Teacher teacher) throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO teachers(name, course, staffNo) VALUES(?, ?, ?)");
        preparedStatement.setString(1, teacher.getName());
        preparedStatement.setString(2, teacher.getCourse());
        preparedStatement.setString(3, teacher.getStaffNo());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Teacher teacher)  throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE teachers SET name = ?, course = ?, staffNo = ? WHERE id = ?");
        preparedStatement.setString(1, teacher.getName());
        preparedStatement.setString(2, teacher.getCourse());
        preparedStatement.setString(3, teacher.getStaffNo());
        preparedStatement.setLong(4, teacher.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean delete(Teacher teacher)  throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM teachers WHERE id = ?");
        preparedStatement.setLong(1, teacher.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM teachers");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Teacher> teachers = new ArrayList<>();
        while (resultSet.next()){
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getLong("id"));
            teacher.setCourse(resultSet.getString("course"));
            teacher.setStaffNo(resultSet.getString("staffNo"));
            teacher.setName(resultSet.getString("name"));
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public Teacher find(long id) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if(resultSet.next()){
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getLong("id"));
            teacher.setCourse(resultSet.getString("course"));
            teacher.setStaffNo(resultSet.getString("staffNo"));
            teacher.setName(resultSet.getString("name"));
            return teacher;
        } else
            return null;
    }

    @Override
    public Teacher find(String staffNo) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE staffNo = ?");
        preparedStatement.setString(1, staffNo);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if(resultSet.next()){
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getLong("id"));
            teacher.setCourse(resultSet.getString("course"));
            teacher.setStaffNo(resultSet.getString("staffNo"));
            teacher.setName(resultSet.getString("name"));
            return teacher;
        } else
            return null;
    }

    public void close() throws SQLException {
        this.dbConnectionI.close();
    }
}
