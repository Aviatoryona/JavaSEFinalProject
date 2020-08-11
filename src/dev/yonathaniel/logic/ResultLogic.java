package dev.yonathaniel.logic;


import dev.yonathaniel.db.DbConnection;
import dev.yonathaniel.db.DbConnectionI;
import dev.yonathaniel.model.result;
import dev.yonathaniel.model.Result;
import dev.yonathaniel.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultLogic implements ResultLogicI {
    private DbConnectionI dbConnectionI;

    public ResultLogic() throws SQLException, ClassNotFoundException {
//        System.out.println("[" + this.getClass().getSimpleName() + "] getting connection...");
        this.dbConnectionI = new DbConnection();
    }

    @Override
    public boolean add(Result result) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO result(studentregno, courseid, score) VALUES(?, ?, ?)");
        preparedStatement.setString(1, result.getStudentRegNo());
        preparedStatement.setInt(2, result.getCourseId());
        preparedStatement.setDouble(3, result.getScore());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Result result) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE result SET score = ?, WHERE id = ?");
        preparedStatement.setDouble(1, result.getScore());
        preparedStatement.setInt(2, result.getId());
        return dbConnectionI.execute(preparedStatement);
    }


    @Override
    public boolean delete(Result result) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM result WHERE id = ?");
        preparedStatement.setLong(1, result.getId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public List<Result> findAll() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM result");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Result> results = new ArrayList<>();
        while (resultSet.next()) {
            Result result = new Result();
            result.setId(resultSet.getInt("id"));
            result.setStudentRegNo(resultSet.getString("studentregno"));
            result.setCourseId(resultSet.getInt("courseid"));
            result.setScore(resultSet.getInt("score"));
            StudentLogicI studentLogicI=new StudentLogic();
            CourseLogicI courseLogic=new CourseLogic();
            result.setStudent(studentLogicI.find(resultSet.getString("studentregno")));
            result.setCourse(courseLogic.find(resultSet.getInt("courseid")));
            results.add(result);
        }

        return results;
    }

    @Override
    public Result find(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if (resultSet.next()) {
            result result = new result();
            result.setId(resultSet.getInt("id"));
            result.setTitle(resultSet.getString("title"));
            result.setresultHours(resultSet.getDouble("resulthours"));
            result.setLevelOfStudy(resultSet.getInt("level"));
            TeacherLogicI teacherLogicI = new TeacherLogic();
            Teacher teacher = teacherLogicI.find(resultSet.getString("teacherstaffno"));
            result.setTeacher(teacher);

            //empty resource
            ((TeacherLogic) teacherLogicI).close();
            return result;
        } else
            return null;
    }

    @Override
    protected void finalize() throws Throwable {
        dbConnectionI.close();
    }
}
