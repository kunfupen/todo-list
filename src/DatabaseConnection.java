import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver Registered!");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo_list", "postgres", "fIc258wan");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        try{
            Connection connection = connect();
            if (connection != null) {
                System.out.println("Connected to the database!");
                connection.close();
            }  else {
                  System.out.println("Failed to make connection!");  
            }
        } catch (SQLException e) {
            System.out.println("Failed to make connection!");
            e.printStackTrace();
        }
    }
}

