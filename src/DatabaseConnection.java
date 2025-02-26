import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/todo_app";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fIc258wan";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try{
            Connection connection = connect();
            if (connection != null) {
                System.out.println("Connected to the database!");
                connection.close();
            } 
        } catch (SQLException e) {
                System.out.println("Failed to make connection!");
                e.printStackTrace();
        }
    }
}
