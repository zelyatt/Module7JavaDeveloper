CREATE TABLE IF NOT EXISTS  workers
(   id SERIAL PRIMARY KEY,
    date_of_birth DATE,
    tech_level VARCHAR(50) CHECK ( tech_level in ('Trainee', 'Junior', 'Middle', 'Senior')),
    salary DECIMAL(10, 2)

);

CREATE TABLE IF NOT EXISTS clients
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS projects
(
    id SERIAL PRIMARY KEY,
    client_id INT references clients(id),
    start_date DATE,
    end_date DATE
);

CREATE TABLE IF NOT EXISTS employee_projects
(
    worker_id INT references workers(id),
    project_id INT references projects(id),
    PRIMARY KEY (worker_id, project_id)
);

ALTER TABLE  workers ADD COLUMN name VARCHAR(50)