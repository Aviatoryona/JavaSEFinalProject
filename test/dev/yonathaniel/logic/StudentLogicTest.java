package dev.yonathaniel.logic;

import dev.yonathaniel.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class StudentLogicTest {
    StudentLogicI studentLogicI;

    public StudentLogicTest() throws SQLException, ClassNotFoundException {
        studentLogicI = new StudentLogic();
    }

    @Test
    public void add() throws SQLException {
        Student student = new Student();
        student.setIdNumber("10101010");
        student.setCourse("FOOD SCIENCE");
        student.setRegistrationNo("FS04/22093/09");
        student.setName("GUSTEAU THE CHEF");
        if (!studentLogicI.add(student))
            Assert.assertFalse(false); // confirmation that it has failed
        Student search = studentLogicI.find(student.getRegistrationNo());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void update() throws SQLException {
        Student search = studentLogicI.find("FS04/22093/09");
        search.setName("CHEF THE GUSTEAU");
        search.setCourse("SCIENCE FOOD");
        search.setIdNumber("01010101");
        if (!studentLogicI.update(search))
            Assert.assertFalse(false); // confirmation that it has failed
        // Means the update was successful. We need to test for correctness of the updated data
        Student student = studentLogicI.find(search.getId());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void delete() throws SQLException {
    }

    @Test
    public void findAll() throws SQLException {
    }

    @Test
    public void find() throws SQLException {

    }
}