package dev.yonathaniel.logic;


import dev.yonathaniel.db.DbConnection;
import dev.yonathaniel.db.DbConnectionI;
import dev.yonathaniel.model.Course;
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
    public boolean add(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO course(title, coursehours, level, teacherstaffno) VALUES(?, ?, ?, ?)");
        preparedStatement.setString(1, course.getTitle());
        preparedStatement.setDouble(2, course.getCourseHours());
        preparedStatement.setInt(3, course.getLevelOfStudy());
        preparedStatement.setString(4, course.getTeacher().getStaffNo());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE course SET title = ?, coursehours = ?, level = ?, teacherstaffno = ? WHERE id = ?");
        preparedStatement.setString(1, course.getTitle());
        preparedStatement.setDouble(2, course.getCourseHours());
        preparedStatement.setInt(3, course.getLevelOfStudy());
        preparedStatement.setString(4, course.getTeacher().getStaffNo());
        preparedStatement.setLong(5, course.getId());
        return dbConnectionI.execute(preparedStatement);
    }


    @Override
    public boolean delete(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM course WHERE id = ?");
        preparedStatement.setLong(1, course.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public List<Course> findAll() throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
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
    public Course find(int id) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if (resultSet.next()) {
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
        dbConnectionI.close();
    }
}
