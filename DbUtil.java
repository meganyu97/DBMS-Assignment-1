

import java.sql.*;
import java.util.Scanner;

//this class has all the methods that take in the input from the main class and calls on the variables in the student class

public class DbUtil {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    public boolean delete (Connection con, int studentID)
            //method for deleting a student from the database given a student ID number
    {
        try
        {
            PreparedStatement pstDelete = con.prepareStatement("DELETE FROM Student WHERE StudentID = ?");
            pstDelete.clearParameters();
            pstDelete.setInt(1, studentID);
            pstDelete.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void displayBy (Connection con, int displayChoice, String value)
            //allows user to choose whether to display students by Major, GPA or faculty advisor
    {
        try {
            if (displayChoice == 1) { //if user chooses to display by Major
                PreparedStatement pst = con.prepareStatement("Select * from Student Where Major = ?");
                pst.clearParameters();
                pst.setString(1, value);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next())
                {
                    System.out.println("Name: " + rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                    System.out.println("ID: " + rs2.getString("StudentId"));
                    System.out.println("GPA: " + rs2.getString("GPA"));
                    System.out.println("Major: " + rs2.getString("Major"));
                    System.out.println("Faculty Advisor: " + rs2.getString("FacultyAdvisor"));
                }
            }
            else if (displayChoice == 2)
            { //if user chooses to display by GPA
                double GPADouble = Double.parseDouble(value); //changes string input to double
                PreparedStatement pst = con.prepareStatement("Select * from Student Where GPA = ?");
                pst.clearParameters();
                pst.setDouble(1, GPADouble);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next())
                {
                    System.out.println("Name: " + rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                    System.out.println("ID: " + rs2.getString("StudentId"));
                    System.out.println("GPA: " + rs2.getString("GPA"));
                    System.out.println("Major: " + rs2.getString("Major"));
                    System.out.println("Faculty Advisor: " + rs2.getString("FacultyAdvisor"));
                }
            }
            else if (displayChoice == 3)
            { //if user chooses to display by faculty advisor
                PreparedStatement pst = con.prepareStatement("Select * from Student Where FacultyAdvisor = ?");
                pst.clearParameters();
                pst.setString(1, value);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next())
                {
                    System.out.println("Name: " + rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                    System.out.println("ID: " + rs2.getString("StudentId"));
                    System.out.println("GPA: " + rs2.getString("GPA"));
                    System.out.println("Major: " + rs2.getString("Major"));
                    System.out.println("Faculty Advisor: " + rs2.getString("FacultyAdvisor"));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void displayAll (Connection con)
    { //displays all students with attributes
        try
        {
            System.out.println("PREPARED STATEMENT");
            PreparedStatement pst = con.prepareStatement("Select * from Student;");
            ResultSet rs2 = pst.executeQuery();

            while (rs2.next())
            {
                System.out.println("Name: " + rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                System.out.println("ID: " + rs2.getString("StudentId"));
                System.out.println("GPA: " + rs2.getString("GPA"));
                System.out.println("Major: " + rs2.getString("Major"));
                System.out.println("Faculty Advisor: " + rs2.getString("FacultyAdvisor"));
            }
        }
        catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
    }

    public void addStudent(Connection con, Student s)
    { //allows user to add student details and this method inserts this student into the datagrip DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO student(FirstName, LastName, GPA, Major, FacultyAdvisor) " +
                                " VALUES(?,?,?,?,?);");

            pstInsert.clearParameters();
            pstInsert.setString(1, s.getFirstName());
            pstInsert.setString(2, s.getLastName());
            pstInsert.setDouble(3, s.getGPA());
            pstInsert.setString(4, s.getMajor());
            pstInsert.setString(5, s.getFacultyAdvisor());
            pstInsert.executeUpdate();
            System.out.println("STUDENT ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateStudent (Student s, Connection con) { //allows user to update either a student's major or advisor given ID
        String updateStatement = null;
        String col, val = null;

        try { //if user chooses to update a student's major
            if(s.getMajor() != null) {
                col = "Major";
                val = s.getMajor();
                updateStatement = "UPDATE student SET Major = ? WHERE StudentID = ?";

                PreparedStatement pstUpdateMajor = con.prepareStatement(updateStatement);
                System.out.println("UPDATE STATEMENT" + updateStatement);

                pstUpdateMajor.clearParameters();
                pstUpdateMajor.setString(1, val);
                pstUpdateMajor.setInt(2, s.getStudentId());

                pstUpdateMajor.executeUpdate();
            }
            else { //if user chooses to update student's faculty advisor
                col = "FacultyAdvisor";
                val = s.getFacultyAdvisor();
                updateStatement = "UPDATE student SET FacultyAdvisor = ? WHERE StudentID = ?";
                PreparedStatement pstUpdateFacultyAdvisor = con.prepareStatement(updateStatement);
                System.out.println("UPDATE STATEMENT" + updateStatement);

                pstUpdateFacultyAdvisor.clearParameters();
                pstUpdateFacultyAdvisor.setString(1, val);
                pstUpdateFacultyAdvisor.setInt(2, s.getStudentId());

                pstUpdateFacultyAdvisor.executeUpdate();
            }

            System.out.println("The student has been updated.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
