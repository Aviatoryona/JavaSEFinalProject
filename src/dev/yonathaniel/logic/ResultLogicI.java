package dev.yonathaniel.logic;

import dev.yonathaniel.model.Result;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface ResultLogicI {

    boolean add(Result result) throws SQLException;

    boolean update(Result result) throws SQLException;

    boolean delete(Result result) throws SQLException;

    Result find(long id) throws SQLException;

    List<Result> findAll(int id) throws SQLException;

    List<Result> findAll() throws SQLException, ClassNotFoundException;
}
