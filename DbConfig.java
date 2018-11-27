

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConfig
{
    private Connection pgSqlConnection = null;

    public static Connection getMySqlConnection()
    {
        Connection mysqlConnection = null;
        try
        {
            //returns the Class object associated with the class or interface with the given string name
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/StudentDb";
            mysqlConnection = DriverManager.getConnection(connectionUrl, "root", "Chapman2018!");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mysqlConnection;
    }
}