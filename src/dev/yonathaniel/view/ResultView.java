package dev.yonathaniel.view;

import dev.yonathaniel.logic.ResultLogic;
import dev.yonathaniel.logic.ResultLogicI;
import dev.yonathaniel.logic.TeacherLogic;
import dev.yonathaniel.model.Result;
import dev.yonathaniel.model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ResultView implements ResultViewI {
    private Scanner scanner;
    private ResultLogicI resultLogicI;

    public ResultView() throws SQLException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        resultLogicI = new ResultLogic();
    }

    //register new result
    private void register() throws SQLException {
        Result result = new Result();
        System.out.println("Enter student registration No:");
        result.setStudentRegNo(scanner.nextLine());
        System.out.println("Enter course id #:");
        result.setCourseId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter score:");
        result.setScore(scanner.nextInt());
        scanner.nextLine();

        System.out.println("You are about to register the following result details:\n" + result.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            resultLogicI.add(result);
    }

    //show registered results
    private void show() throws SQLException, ClassNotFoundException {
        System.out.println("List of results from the DB");
        List<Result> results = resultLogicI.findAll();
        for (Result result : results) {
            System.out.println(result.toStringRow());
        }
    }

    /**/
    @Override
    public void menu() throws SQLException, ClassNotFoundException {
        int option;
        do {
            System.out.println("Welcome to results Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a result \n" +
                    "2. Edit a result \n" +
                    "3. Delete a result \n" +
                    "4. Show list of results \n" +
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
        resultLogicI = null;
    }

    //update results info
    private void update() throws SQLException, ClassNotFoundException {
        System.out.println("Update results info");
        System.out.println("Enter result ID #:");
        int id = scanner.nextInt();
        Result result = resultLogicI.findResult(id);
        if (result == null) {
            System.out.println("result not registered");
            return;
        }
        System.out.println("Enter new score:");
        result.setScore(scanner.nextDouble());
        System.out.println("You are about to update the following results' details:\n" + result.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            resultLogicI.update(result);
    }

    //Remove result from DB
    private void delete() throws SQLException, ClassNotFoundException {
        System.out.println("Delete result and all related information");
        System.out.println("Enter result ID #:");
        int id = scanner.nextInt();
        Result result = resultLogicI.findResult(id);
        if (result == null) {
            System.out.println("result not registered");
            return;
        }
        System.out.println("You are about to remove the following results' details:\n" + result.toString() + "\n. Action cannot be reversed.Continue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            resultLogicI.delete(result);
    }
}
