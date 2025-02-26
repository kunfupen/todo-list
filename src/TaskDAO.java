import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    
    public static void addTask(String name, String description, String dueDate, String status) {
        String sql = "INSERT INTO tasks (name, description, due_date, status) VALUES (?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, dueDate);
            stmt.setString(4, status);
            stmt.executeUpdate();
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add task!");
            e.printStackTrace();
        }
    }

    public static List<Task> getTasks() {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String dueDate = rs.getString("due_date");
                String status = rs.getString("status");
                tasks.add(new Task(id, name, description, dueDate, status));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get tasks!");
            e.printStackTrace();
        }

        return tasks;
    }

    public static void updateTask(int id, String name, String status) {
        String sql = "UPDATE tasks SET name = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, status);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Task not found!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update task!");
            e.printStackTrace();
        }
    }

    public static void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("Task not found!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete task!");
            e.printStackTrace();
        }
    }
}
