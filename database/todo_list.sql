-- Creates the database if it does not exist
DO
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'todo_list') THEN
        PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE todo_list');
    END IF;
END
$$;

-- Connect to the database
\c todo_list;

-- Create the table if it does not exist
DO
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'tasks') THEN
        CREATE TABLE tasks (
            id SERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            description TEXT,
            due_date DATE,
            status VARCHAR(50) DEFAULT 'Pending'
        );
    END IF;
END
$$;

-- Truncate the table to remove all existing tasks
TRUNCATE TABLE tasks;