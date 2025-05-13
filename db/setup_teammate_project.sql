-- Connect to the default postgres database
\c postgres;

-- 1. Create a new database for the Teammate project
CREATE DATABASE teammate_db;

-- 2. Create a new user for the Teammate project
CREATE USER teammate_user WITH PASSWORD 'tupass';

-- 3. Grant all privileges to the new user on the new database
GRANT ALL PRIVILEGES ON DATABASE teammate_db TO teammate_user;

-- 4. Connect to the new database
\c teammate_db;

-- 5. Create the members table to store team member details
CREATE TABLE members (
    id SERIAL PRIMARY KEY,         -- Unique ID for each member
    name VARCHAR(100) NOT NULL,    -- Name of the member
    role VARCHAR(100),             -- Role of the member (e.g., Developer, Manager)
    age INT,                       -- Age of the member
    email VARCHAR(255),            -- Email of the member
    joined_date DATE               -- The date when the member joined
);

-- 6. Create the teams table to store team information
CREATE TABLE teams (
    id SERIAL PRIMARY KEY,         -- Unique ID for each team
    team_name VARCHAR(100) NOT NULL,  -- Team name (e.g., Frontend, Backend)
    created_date DATE              -- Date when the team was created
);

-- 7. Create the member_team table to establish the many-to-many relationship between members and teams
CREATE TABLE member_team (
    member_id INT REFERENCES members(id) ON DELETE CASCADE, -- Foreign key to members table
    team_id INT REFERENCES teams(id) ON DELETE CASCADE,     -- Foreign key to teams table
    PRIMARY KEY (member_id, team_id)                        -- Composite primary key
);

-- 8. Create the tasks table to store tasks assigned to team members
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,            -- Unique task ID
    title VARCHAR(255) NOT NULL,       -- Task title
    description TEXT,                  -- Task description
    assigned_to INT REFERENCES members(id) ON DELETE SET NULL,  -- Task assigned to a member
    due_date DATE,                     -- Task due date
    status VARCHAR(50) DEFAULT 'Open'  -- Task status (e.g., Open, In Progress, Completed)
);

-- 9. Create the project table to store information about different projects
CREATE TABLE projects (
    id SERIAL PRIMARY KEY,            -- Unique project ID
    project_name VARCHAR(255) NOT NULL, -- Name of the project
    description TEXT,                  -- Project description
    start_date DATE,                   -- Start date of the project
    end_date DATE                      -- End date of the project
);

-- 10. Create the project_member table to store project assignments (many-to-many)
CREATE TABLE project_member (
    project_id INT REFERENCES projects(id) ON DELETE CASCADE,  -- Foreign key to projects table
    member_id INT REFERENCES members(id) ON DELETE CASCADE,    -- Foreign key to members table
    role VARCHAR(100),               -- Role of the member in the project
    PRIMARY KEY (project_id, member_id)  -- Composite primary key
);

-- 11. Create the logs table to store activity logs for auditing
CREATE TABLE logs (
    id SERIAL PRIMARY KEY,          -- Unique log ID
    action VARCHAR(255) NOT NULL,   -- Action performed (e.g., 'Added member', 'Task completed')
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- When the action took place
    performed_by INT REFERENCES members(id) ON DELETE SET NULL -- Who performed the action
);

-- 12. Optional: Display success message once all tables are created
\echo 'Teammate database and tables created successfully.';
