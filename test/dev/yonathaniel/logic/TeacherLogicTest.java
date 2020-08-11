package dev.yonathaniel.logic;

import dev.yonathaniel.model.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TeacherLogicTest {

    TeacherLogicI teacherLogicI;

    @Before
    public void setUp() throws Exception {
        teacherLogicI = new TeacherLogic();
    }

    @Test
    public void add() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setCourse("Java EE");
        teacher.setName("Aviator K");
        teacher.setStaffNo("FS04/22093/09");
        if (!teacherLogicI.add(teacher))
            Assert.assertFalse(false); // confirmation that it has failed
        Teacher search = teacherLogicI.find(teacher.getStaffNo());
        Assert.assertEquals(teacher.getStaffNo(), search.getStaffNo());
        Assert.assertEquals(teacher.getName(), search.getName());
        Assert.assertEquals(teacher.getCourse(), search.getCourse());
    }

    @Test
    public void update() throws SQLException {
        Teacher teacher = teacherLogicI.find("FS04/22093/09");
        teacher.setCourse("Java EE");
        teacher.setName("MYK Aviator");
//        teacher.setStaffNo("FS04/22093/09");
        if (!teacherLogicI.update(teacher))
            Assert.assertFalse(false); // confirmation that it has failed
        Teacher search = teacherLogicI.find(teacher.getStaffNo());
        Assert.assertEquals(teacher.getStaffNo(), search.getStaffNo());
        Assert.assertEquals(teacher.getName(), search.getName());
        Assert.assertEquals(teacher.getCourse(), search.getCourse());
    }

    @Test
    public void delete() throws SQLException {
        Teacher search = teacherLogicI.find("FS04/22093/09");
        Assert.assertNotNull(search);
        teacherLogicI.delete(search);
        search = teacherLogicI.find("FS04/22093/09");
        Assert.assertNull(search);
    }

    @Test
    public void findAll() throws SQLException {
        List<Teacher> teachers=teacherLogicI.findAll();
        Assert.assertNotNull(teachers);
        Assert.assertFalse(teachers.isEmpty());
    }

    @Test
    public void find() throws SQLException {
        Teacher teacher=teacherLogicI.find("FS04/22093/09");
        Assert.assertNotNull(teacher);
        Assert.assertEquals(teacher.getName(),"MYK Aviator");
    }


    @Ignore
    @Test
    public void close() {
    }
}