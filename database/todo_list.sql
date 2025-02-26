CREATE DATABASE todo_list;

\c todo_list;

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(50) DEFAULT 'Pending'
);

 
INSERT INTO tasks (title, description, due_date, status)
VALUES 
('Finish Java project', 'Complete CRUD function', '2025-02-27', 'In Progress'),
('Study for interview', 'Review Java + SQL Leetcode + Understand Resume', '2025-02-28', 'Pending'),
('SOCI HW', 'Start SOCI HW', '2025-02-28', 'Pending');
