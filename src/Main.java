import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        try {
            TaskDAO.addTask("Finish Java project", "Complete CRUD function", "2025-02-27", "Pending");

            System.out.println("\nTasks List:");
            List<Task> tasks = TaskDAO.getTasks();
            for (Task task : tasks) {
                System.out.println("ID: "+ task.getID() + ", Name: " + task.getName() + ", Status: " + task.getStatus());
            }
            
            if(tasks.size() > 0) {
                TaskDAO.updateTask(tasks.get(0).getID(), "Finish Java project - Updated", "In Progress");
            } else {
                System.out.println("No tasks to update!");
            }

            if(tasks.size() > 0) {
                TaskDAO.deleteTask(tasks.get(0).getID());
            } else {
                System.out.println("No tasks to delete!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
