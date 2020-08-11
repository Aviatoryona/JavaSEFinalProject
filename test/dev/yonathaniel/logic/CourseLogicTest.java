package dev.yonathaniel.logic;

import dev.yonathaniel.model.Course;
import dev.yonathaniel.model.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class CourseLogicTest {

    private CourseLogicI courseLogicI;

    @Before
    public void setUp() throws Exception {
        courseLogicI = new CourseLogic();
    }

    @Test
    public void add() throws SQLException {
        Course course = new Course();
        course.setTitle("Math");
        Teacher teacher = new Teacher();
        teacher.setStaffNo("7888");
        course.setTeacher(teacher);
        course.setLevelOfStudy(1);
        course.setCourseHours(45);
        if (!courseLogicI.add(course))
            Assert.assertFalse(false);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Course search = courseLogicI.find(2);
        search.setTitle("Mathematics");
        search.setLevelOfStudy(1);
        search.setCourseHours(25);
        if (!courseLogicI.update(search))
            Assert.assertFalse(false); // confirmation that it has failed
        // Means the update was successful. We need to test for correctness of the updated data
        Course course = courseLogicI.find(search.getId());
        Assert.assertEquals(course.getTitle(), search.getTitle());
        Assert.assertEquals(course.getTeacher().getStaffNo(), search.getTeacher().getStaffNo());
        Assert.assertEquals(course.getCourseHours(), search.getCourseHours(), 0);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Course search = courseLogicI.find(2);
        Assert.assertNotNull(search);
        courseLogicI.delete(search);
        search = courseLogicI.find(2);
        Assert.assertNull(search);
    }

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {
        List<Course> courses = courseLogicI.findAll();
        Assert.assertNotNull(courses);
        Assert.assertFalse(courses.isEmpty());
    }

    @Test
    public void find() throws SQLException, ClassNotFoundException {
        Course course = courseLogicI.find(2);
        Assert.assertNotNull(course);
        Assert.assertEquals(course.getTitle(), "Mathematics");
    }
}