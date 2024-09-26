WITH worker_project_counts AS (
    SELECT ep.worker_id, COUNT(DISTINCT ep.project_id) AS project_count
    FROM employee_projects ep
    GROUP BY ep.worker_id
),

     max_projects AS (
         SELECT MAX(project_count) AS max_count
         FROM worker_project_counts
     )

SELECT w.id, w.name, wpc.project_count
FROM workers w
         JOIN worker_project_counts wpc ON w.id = wpc.worker_id
         JOIN max_projects mp ON wpc.project_count = mp.max_count
ORDER BY w.id;
