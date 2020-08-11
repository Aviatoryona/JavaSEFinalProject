package dev.yonathaniel;

import dev.yonathaniel.view.CourseView;
import dev.yonathaniel.view.ResultView;
import dev.yonathaniel.view.StudentView;
import dev.yonathaniel.view.TeacherView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Welcome to School System:\n Please select an option:" +
                    "\n1. Manage Teachers" +
                    "\n2. Manage Students" +
                    "\n3. Manage Courses" +
                    "\n4. Manage Results" +
                    "\n5. Exit");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    new TeacherView().menu();
                    break;
                case 2:
                    new StudentView().menu();
                    break;
                case 3:
                    new CourseView().menu();
                    break;
                case 4:
                    new ResultView().menu();
                    break;
                case 5:
                    System.out.println("Bye!!");
                    break;
            }
        } while (option != 5);
    }
}
