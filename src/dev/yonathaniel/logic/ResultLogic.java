package dev.yonathaniel.logic;


import dev.yonathaniel.db.DbConnection;
import dev.yonathaniel.db.DbConnectionI;
import dev.yonathaniel.model.Result;

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
    public Result findResult(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM result WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if (resultSet.next()) {
            Result result = new Result();
            result.setId(resultSet.getInt("id"));
            result.setStudentRegNo(resultSet.getString("studentregno"));
            result.setCourseId(resultSet.getInt("courseid"));
            result.setScore(resultSet.getInt("score"));
            StudentLogicI studentLogicI = new StudentLogic();
            CourseLogicI courseLogic = new CourseLogic();
            result.setStudent(studentLogicI.find(resultSet.getString("studentregno")));
            result.setCourse(courseLogic.find(resultSet.getInt("courseid")));
            return result;
        }

        return null;
    }


    @Override
    public List<Result> findAll(int courseId) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM result WHERE courseid=?");
        preparedStatement.setLong(1, courseId);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Result> results = new ArrayList<>();
        while (resultSet.next()) {
            Result result = new Result();
            result.setId(resultSet.getInt("id"));
            result.setStudentRegNo(resultSet.getString("studentregno"));
            result.setCourseId(resultSet.getInt("courseid"));
            result.setScore(resultSet.getInt("score"));
            StudentLogicI studentLogicI = new StudentLogic();
            CourseLogicI courseLogic = new CourseLogic();
            result.setStudent(studentLogicI.find(resultSet.getString("studentregno")));
            result.setCourse(courseLogic.find(resultSet.getInt("courseid")));
            results.add(result);
        }

        return results;
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
            StudentLogicI studentLogicI = new StudentLogic();
            CourseLogicI courseLogic = new CourseLogic();
            result.setStudent(studentLogicI.find(resultSet.getString("studentregno")));
            result.setCourse(courseLogic.find(resultSet.getInt("courseid")));
            results.add(result);
        }

        return results;
    }

    @Override
    public List<Result> find(String studentRegNo) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM result WHERE studentregno = ?");
        preparedStatement.setString(1, studentRegNo);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Result> results = new ArrayList<>();
        while (resultSet.next()) {
            Result result = new Result();
            result.setId(resultSet.getInt("id"));
            result.setStudentRegNo(resultSet.getString("studentregno"));
            result.setCourseId(resultSet.getInt("courseid"));
            result.setScore(resultSet.getInt("score"));
            StudentLogicI studentLogicI = new StudentLogic();
            CourseLogicI courseLogic = new CourseLogic();
            result.setStudent(studentLogicI.find(resultSet.getString("studentregno")));
            result.setCourse(courseLogic.find(resultSet.getInt("courseid")));
            results.add(result);
        }

        return results;
    }

    @Override
    protected void finalize() throws Throwable {
        dbConnectionI.close();
    }
}
