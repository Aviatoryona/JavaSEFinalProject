package dev.yonathaniel.view;

import dev.yonathaniel.logic.TeacherLogic;
import dev.yonathaniel.logic.TeacherLogicI;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TeacherView implements TeacherViewI {
    private Scanner scanner;
    private TeacherLogicI teacherLogicI;

    public TeacherView() throws SQLException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        teacherLogicI = new TeacherLogic();
    }

    //register new teacher
    private void register() throws SQLException {
        Teacher teacher = new Teacher();
        System.out.println("Enter name:");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter staff number #:");
        teacher.setStaffNo(scanner.nextLine());
        System.out.println("Enter course:");
        teacher.setCourse(scanner.nextLine());
        System.out.println("You are about to register the following details:\n" + teacher.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            teacherLogicI.add(teacher);
    }

    //show registered teachers
    private void show() throws SQLException {
        System.out.println("List of students from the DB");
        List<Teacher> teachers = teacherLogicI.findAll();
        Iterator iterator = teachers.iterator();
        while (iterator.hasNext()) {
            System.out.println(((Teacher) iterator.next()).toStringRow());
        }
    }

    @Override
    public void menu() throws SQLException {
        int option;
        do {
            System.out.println("Welcome to Student Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a student \n" +
                    "2. Edit a student \n" +
                    "3. Delete a student \n" +
                    "4. Show list of students \n" +
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
        teacherLogicI = null;
    }

    //update teachers info
    private void update() throws SQLException {
        System.out.println("Update students info");
        System.out.println("Enter registration #:");
        String reg = scanner.nextLine();
        Teacher teacher = teacherLogicI.find(reg);
        if (teacher == null) {
            System.out.println("Student not registered");
            return;
        }
        System.out.println("Enter new name:");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter new course:");
        teacher.setCourse(scanner.nextLine());
        System.out.println("You are about to update the following teachers' details:\n" + teacher.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            teacherLogicI.update(teacher);
    }

    //Remove teacher from DB
    private void delete() throws SQLException {
        System.out.println("Delete student and all related information");
        System.out.println("Enter registration #:");
        String reg = scanner.nextLine();
        Teacher teacher = teacherLogicI.find(reg);
        if (teacher == null) {
            System.out.println("Teacher not registered");
            return;
        }
        System.out.println("You are about to remove the following teachers' details:\n" + teacher.toString() + "\n. Action cannot be reversed.Continue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            teacherLogicI.delete(teacher);
    }
}
