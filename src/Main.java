import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter Task Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Task Description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter Due Date (YYYY-MM-DD):");
                    String dueDate = scanner.nextLine();
                    System.out.println("Enter Task Status:");
                    String status = scanner.nextLine();

                    TaskDAO.addTask(name, description, dueDate, status);
                    break;

                case 2:
                    TaskDAO.getTasks().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter Task ID to update:");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.println("Enter new Task Name:");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new Task Status:");
                    String newStatus = scanner.nextLine();
                    TaskDAO.updateTask(taskId, newName, newStatus);
                    break;

                case 4:
                    System.out.println("Enter Task ID to delete:");
                    int deleteId = scanner.nextInt();
                    TaskDAO.deleteTask(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
