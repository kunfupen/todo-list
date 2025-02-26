CREATE DATABASE todo_list;

USE todo_list;

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATA,
    status ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
);

INSERT INTO tasks (title, description, due_datem status)
VALUES 
('Finish Java project', 'Complete CRUD function', '2025-02-27', 'In Progress'),
('Study for interview', 'Review Java + SQL Leetcode + Understand Resume', '2025-02-28', 'Pending'),
('SOCI HW', 'Start SOCI HW', '2025-02-28', 'Pending');
