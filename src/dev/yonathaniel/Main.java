package dev.yonathaniel;

import dev.yonathaniel.view.StudentView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;
        StudentView studentView = new StudentView();
        do {
            System.out.println("Welcome to School System:\n Please select an option:\n1. Manage Teachers\n2. Manage Students\n3. Exit");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    break;
                case 2:
                    studentView.menu();
                    break;
            }
        } while(option != 3);
        studentView = null;
    }
}
