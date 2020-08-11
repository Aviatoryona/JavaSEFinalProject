package dev.yonathaniel.logic;

import dev.yonathaniel.model.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ResultLogicTest {

    ResultLogicI resultLogicI;

    @Before
    public void setUp() throws Exception {
        resultLogicI = new ResultLogic();
    }

    @Test
    public void add() throws SQLException {
        Result result = new Result();
        result.setStudentRegNo("FS04/22093/09");
        result.setCourseId(2);
        result.setScore(50);
        if (!resultLogicI.add(result))
            Assert.assertFalse(false);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Result search = resultLogicI.findResult(2);
        search.setScore(90);
        if (!resultLogicI.update(search))
            Assert.assertFalse(false); // confirmation that it has failed
        // Means the update was successful. We need to test for correctness of the updated data
        Result result = resultLogicI.findResult(search.getId());
        Assert.assertEquals(result.getScore(), 90, 0);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Result search = resultLogicI.findResult(2);
        Assert.assertNotNull(search);
        resultLogicI.delete(search);
        search = resultLogicI.findResult(2);
        Assert.assertNull(search);
    }

    @Test
    public void findResult() throws SQLException, ClassNotFoundException {
        Result search = resultLogicI.findResult(2);
        Assert.assertNotNull(search);
    }

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {
        List<Result> search = resultLogicI.findAll();
        Assert.assertNotNull(search);
        Assert.assertFalse(search.isEmpty());
    }

}