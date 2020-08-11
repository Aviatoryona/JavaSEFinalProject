package dev.yonathaniel.logic;


import dev.yonathaniel.db.DbConnection;
import dev.yonathaniel.db.DbConnectionI;
import dev.yonathaniel.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseLogic implements CourseLogicI {
    private DbConnectionI dbConnectionI;

    public CourseLogic() throws SQLException, ClassNotFoundException {
//        System.out.println("[" + this.getClass().getSimpleName() + "] getting connection...");
        this.dbConnectionI = new DbConnection();
    }

    @Override
    public boolean add(Student student) throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO students(name, registrationNo, course, idNumber) VALUES(?, ?, ?, ?)");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getRegistrationNo());
        preparedStatement.setString(3, student.getCourse());
        preparedStatement.setString(4, student.getIdNumber());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Student student)  throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE students SET name = ?, registrationNo = ?, course = ?, idNumber = ? WHERE id = ?");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getRegistrationNo());
        preparedStatement.setString(3, student.getCourse());
        preparedStatement.setString(4, student.getIdNumber());
        preparedStatement.setLong(5, student.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean delete(Student student)  throws SQLException{
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM students WHERE id = ?");
        preparedStatement.setLong(1, student.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public List<Student> findAll() throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setCourse(resultSet.getString("course"));
            student.setIdNumber(resultSet.getString("idNumber"));
            student.setName(resultSet.getString("name"));
            student.setRegistrationNo(resultSet.getString("registrationNo"));
            students.add(student);
        }
        return students;
    }

    @Override
    public Student find(long id) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if(resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setCourse(resultSet.getString("course"));
            student.setIdNumber(resultSet.getString("idNumber"));
            student.setName(resultSet.getString("name"));
            student.setRegistrationNo(resultSet.getString("registrationNo"));
            return student;
        } else
            return null;
    }

    @Override
    public Student find(String registrationNo) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE registrationNo = ?");
        preparedStatement.setString(1, registrationNo);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if(resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setCourse(resultSet.getString("course"));
            student.setIdNumber(resultSet.getString("idNumber"));
            student.setName(resultSet.getString("name"));
            student.setRegistrationNo(resultSet.getString("registrationNo"));
            return student;
        } else
            return null;
    }

    @Override
    protected void finalize() throws Throwable {
//        System.out.println("[" + this.getClass().getSimpleName() + "] closing connection...");
        dbConnectionI.close();
    }
}
