package dev.yonathaniel.view;

import dev.yonathaniel.logic.CourseLogic;
import dev.yonathaniel.logic.CourseLogicI;
import dev.yonathaniel.logic.TeacherLogic;
import dev.yonathaniel.logic.TeacherLogicI;
import dev.yonathaniel.model.Course;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CourseView implements CourseViewI {
    private Scanner scanner;
    private CourseLogicI courseLogicI;

    public CourseView() throws SQLException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        courseLogicI = new CourseLogic();
    }

    //register new course
    private void register() throws SQLException, ClassNotFoundException {
        Course course = new Course();
        System.out.println("Enter title:");
        course.setTitle(scanner.nextLine());
        System.out.println("Enter course hours #:");
        course.setCourseHours(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Enter course level of study:");
        course.setLevelOfStudy(scanner.nextInt());
        scanner.nextLine();
        Teacher teacher;
        do {
            System.out.println("Enter course teacher staff Number:");
            teacher = new TeacherLogic().find(scanner.nextLine());
            if (teacher == null)
                System.err.println("Teacher not found");
            else course.setTeacher(teacher);
        } while (teacher == null);

        System.out.println("You are about to register the following course details:\n" + course.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            courseLogicI.add(course);
    }

    //show registered courses
    private void show() throws SQLException, ClassNotFoundException {
        System.out.println("List of courses from the DB");
        List<Course> courses = courseLogicI.findAll();
        for (Course course : courses) {
            System.out.println(course.toStringRow());
        }
    }

    /**/
    @Override
    public void menu() throws SQLException, ClassNotFoundException {
        int option;
        do {
            System.out.println("Welcome to courses Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a course \n" +
                    "2. Edit a course \n" +
                    "3. Delete a course \n" +
                    "4. Show list of courses \n" +
                    "0. Back to main menu \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    this.register();
                    break;
                case 2:
                    // edit();
                    this.update();
                    break;
                case 3:
                    // delete();
                    this.delete();
                    break;
                case 4:
                    this.show();
                    break;
                case 0:
                    break;
            }
        } while (option != 0);
        courseLogicI = null;
    }

    //update courses info
    private void update() throws SQLException, ClassNotFoundException {
        System.out.println("Update courses info");
        System.out.println("Enter course ID #:");
        int id = scanner.nextInt();
        Course course = courseLogicI.find(id);
        if (course == null) {
            System.out.println("course not registered");
            return;
        }
        System.out.println("Enter new title:");
        course.setTitle(scanner.nextLine());
        System.out.println("Enter course hours #:");
        course.setCourseHours(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Enter course level of study:");
        course.setLevelOfStudy(scanner.nextInt());
        scanner.nextLine();
        Teacher teacher;
        do {
            System.out.println("Enter course teacher staff Number:");
            teacher = new TeacherLogic().find(scanner.nextLine());
            if (teacher == null)
                System.err.println("Teacher not found");
            else course.setTeacher(teacher);
        } while (teacher == null);
        System.out.println("You are about to update the following courses' details:\n" + course.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            courseLogicI.update(course);
    }

    //Remove course from DB
    private void delete() throws SQLException, ClassNotFoundException {
        System.out.println("Delete course and all related information");
        System.out.println("Enter course ID #:");
        int id = scanner.nextInt();
        Course course = courseLogicI.find(id);
        if (course == null) {
            System.out.println("course not registered");
            return;
        }
        System.out.println("You are about to remove the following courses' details:\n" + course.toString() + "\n. Action cannot be reversed.Continue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            courseLogicI.delete(course);
    }
}
