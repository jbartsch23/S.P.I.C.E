import java.sql.*;

public class Common {
    public static Connection makeDBConnection() { // establish connection
        Connection conn = null;
        // change connection details for the server on pi
        String dbConnectionString = "jdbc:sqlserver://localhost:1433;databaseName=Recipes;encrypt=true;trustServerCertificate=true";
        String userName = "spice_user";
        String password = "spice";

        try {
            conn = DriverManager.getConnection(dbConnectionString, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully.");
        return conn;
    }

    public static void closeDBConnection(Connection conn){ //close connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } 
        catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        }
    }

    public static void main(String[] args) {
        Connection conn = makeDBConnection(); // test connection
        closeDBConnection(conn);
    }
}