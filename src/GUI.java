import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, descriptionField, dueDateField, statusField, idField;

    public GUI() {

        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Due Date", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        dueDateField = new JTextField();
        inputPanel.add(dueDateField);
        inputPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        inputPanel.add(statusField);
        frame.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Task");
        JButton updateButton = new JButton("Update Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton refreshButton = new JButton("Refresh");

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(refreshButton);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> {
            TaskDAO.addTask(nameField.getText(), descriptionField.getText(), dueDateField.getText(), statusField.getText());
            refreshTable();
        });

        updateButton.addActionListener(e -> {
            try { 
                int id = Integer.parseInt(idField.getText());
                TaskDAO.updateTask(id, nameField.getText(), statusField.getText());
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                TaskDAO.deleteTask(id);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });

        refreshButton.addActionListener(e -> refreshTable());
        
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                idField.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                nameField.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                descriptionField.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                dueDateField.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                statusField.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
            }
        });

        refreshTable();
        frame.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Task> tasks = TaskDAO.getTasks();
        for (Task task : tasks) {
            tableModel.addRow(new Object[]{task.getID(), task.getName(), task.getDescription(), task.getDueDate(), task.getStatus()});
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

}
