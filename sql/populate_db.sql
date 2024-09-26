INSERT INTO workers(date_of_birth, tech_level, salary) VALUES
('1990-05-15', 'Trainee', 800),
('1992-11-20', 'Junior', 1200),
('1988-02-12', 'Middle', 3000),
('1985-07-07', 'Senior', 6000),
('1995-08-19', 'Trainee', 900),
('1993-03-25', 'Junior', 1500),
('1987-01-30', 'Middle', 3500),
('1984-12-15', 'Senior', 7000),
('1996-09-10', 'Junior', 1100),
('1991-04-22', 'Senior', 5500);

INSERT INTO clients(name) VALUES
('AT corp'),
('Cinno corp'),
('Google'),
('Cushman'),
('Flex');

INSERT INTO projects (client_id, start_date, end_date) VALUES
(1, '2021-01-01', '2023-01-01'),
(2, '2022-03-15', '2023-04-15'),
(3, '2020-06-01', '2021-06-01'),
(4, '2023-05-01', '2023-07-01'),
(5, '2021-09-10', '2022-09-10'),
(1, '2019-11-20', '2022-11-20'),
(2, '2020-01-01', '2023-01-01'),
(3, '2022-05-01', '2023-11-01'),
(4, '2021-04-01', '2021-05-01'),
(5, '2023-01-01', '2024-01-01');

INSERT INTO employee_projects (worker_id, project_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 3),
(7, 3),
(8, 4),
(9, 4),
(10, 5),
(1, 6),
(2, 7),
(3, 8),
(4, 9),
(5, 10),
(6, 10),
(7, 5),
(8, 6),
(9, 7),
(10, 8);

UPDATE workers
SET name = CASE id
               WHEN 1 THEN 'Vlad'
               WHEN 2 THEN 'Liza'
               WHEN 3 THEN 'Mykola'
               WHEN 4 THEN 'Magda'
               WHEN 5 THEN 'Piotr'
               WHEN 6 THEN 'Olena'
               WHEN 7 THEN 'Yurii'
               WHEN 8 THEN 'Oleg'
               WHEN 9 THEN 'Sergii'
               WHEN 10 THEN 'Volodymyr'
               ELSE name
    END;
