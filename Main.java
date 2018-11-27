
//This assignment creates a java application that accesses and can update data in a database of students and their attributes.

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.io.Console;

public class Main {
    private static Check numberCheck = new Check();

    public static void main(String[] argv) {
        boolean keepGoing = true;
        while (keepGoing) {
            Connection con = null;
            try {
                con = DbConfig.getMySqlConnection();

                if (con.isClosed()) {
                    System.out.println("Connection was closed. creating new connection to MySQL");
                    con = DbConfig.getMySqlConnection();
                }

                DbUtil class2 = new DbUtil();

                String FirstName;
                String LastName;
                double GPA;
                String Major;
                String FacultyAdvisor;

                Statement st = null;
                ResultSet rs = null;
                Student s = null;
                int choice = 0;
                String column = null;

                Scanner keyboard = new Scanner(System.in);
                System.out.println("Please select numerical which option you would like to perform.");
                System.out.println("(1) - Display all students and their attributes.");
                System.out.println("(2) - Add a new student to the database.");
                System.out.println("(3) - Update a student's major or advisor.");
                System.out.println("(4) - Delete a student from the database.");
                System.out.println("(5) - Display students by major/GPA/advisor.");
                System.out.println("(6) - Exit.");

                choice = numberCheck.integerCheck();
                while (choice > 6 || choice < 1) {
                    System.out.println("Please enter a number between 1 and 6.");
                    choice = numberCheck.integerCheck();

                }

                switch (choice) {
                    case 1:
                        class2.displayAll(con);
                        break;
                    case 2:
                        System.out.println("Enter student first name: ");
                        //keyboard.nextLine();
                        FirstName = keyboard.nextLine();

                        System.out.println("Enter the student last name: ");
                        LastName = keyboard.nextLine();

                        System.out.println("Enter the student GPA: ");

                        GPA = numberCheck.doubleCheck();

                        System.out.println("Enter the student major: ");
                        //keyboard.nextLine();
                        Major = keyboard.nextLine();

                        System.out.println("Enter the student faculty advisor: ");
                        //keyboard.nextLine();
                        FacultyAdvisor = keyboard.nextLine();

                        s = new Student(FirstName, LastName, GPA, Major, FacultyAdvisor);

                        class2.addStudent(con, s);
                        break;

                    case 3:
                        System.out.println("Enter student ID to update student in database: ");
                        int StudentID = numberCheck.integerCheck();

                        System.out.println("What would you like to update?");
                        System.out.println("(1) - Major");
                        System.out.println("(2) - Advisor");
                        System.out.println("Choice: ");

                        int updateChoice = numberCheck.integerCheck();
                        while (updateChoice > 2 || updateChoice < 1) {
                            System.out.println("Please enter a number between 1 and 2.");
                            updateChoice = numberCheck.integerCheck();
                        }

                        if (updateChoice == 1) {
                            System.out.println("Enter the student's NEW major: ");
                            Major = keyboard.nextLine();
                            s = new Student();
                            s.setMajor(Major);
                            s.setStudentId(StudentID);
                        } else if (updateChoice == 2) {
                            s = new Student();
                            s.setMajor(null);
                            s.setStudentId(StudentID);
                            System.out.print("Enter the student's NEW advisor: ");
                            FacultyAdvisor = keyboard.nextLine();
                            s.setFacultyAdvisor(FacultyAdvisor);
                        }

                        class2.updateStudent(s, con);
                        break;

                    case 4:
                        System.out.println("Enter student ID to delete student from database: ");
                        StudentID = keyboard.nextInt();
                        class2.delete(con, StudentID);
                        if (class2.delete(con, StudentID))
                            System.out.println("DELETE WAS SUCCESSFUL");
                        break;

                    case 5:
                        System.out.println("How would you like to display students by?");
                        System.out.println("(1) - Major");
                        System.out.println("(2) - GPA");
                        System.out.println("(3) - Advisor");
                        System.out.println("Choice: ");

                        int displayChoice = numberCheck.integerCheck();
                        while (displayChoice > 3 || displayChoice < 1) {
                            System.out.println("Please enter a number between 1 and 3");
                            displayChoice = numberCheck.integerCheck();
                        }

                        if (displayChoice == 1) {
                            System.out.println("Enter the major to display: ");
                            String displayMajor = keyboard.nextLine();
                            class2.displayBy(con, 1, displayMajor);
                        } else if (displayChoice == 2) {
                            System.out.println("Enter the GPA to display: ");
                            String displayGPA = keyboard.nextLine();
                            class2.displayBy(con, 2, displayGPA);
                        } else if (displayChoice == 3) {
                            System.out.println("Enter the faculty advisor to display: ");
                            String displayAdvisor = keyboard.nextLine();
                            class2.displayBy(con, 3, displayAdvisor);
                        }
                        break;
                    case 6:
                        keepGoing = false;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
