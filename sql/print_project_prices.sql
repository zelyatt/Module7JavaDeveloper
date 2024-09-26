WITH project_durations AS (
    SELECT id AS project_id,
           (EXTRACT(YEAR FROM age(end_date, start_date)) * 12 +
            EXTRACT(MONTH FROM age(end_date, start_date))) AS duration_months
    FROM projects
),

     project_salaries AS (
         SELECT ep.project_id,
                SUM(w.salary) AS total_salary
         FROM employee_projects ep
                  JOIN workers w ON ep.worker_id = w.id
         GROUP BY ep.project_id
     ),

     project_prices AS (
         SELECT p.id AS project_id,
                p.client_id,
                p.start_date,
                p.end_date,
                pd.duration_months,
                ps.total_salary,
                pd.duration_months * ps.total_salary AS price
         FROM projects p
                  JOIN project_durations pd ON p.id = pd.project_id
                  JOIN project_salaries ps ON p.id = ps.project_id
     )

SELECT c.name AS name,
       pp.price AS price
FROM project_prices pp
         JOIN clients c ON pp.client_id = c.id
ORDER BY pp.price DESC;
