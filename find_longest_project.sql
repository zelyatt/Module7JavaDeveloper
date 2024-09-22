WITH project_durations AS (
    SELECT id,
           client_id,
           DATE_PART('year', AGE(end_date, start_date)) * 12 +
           DATE_PART('month', AGE(end_date, start_date)) AS month_count
    FROM projects
),

     max_duration AS (
         SELECT MAX(month_count) AS max_months
         FROM project_durations
     )
SELECT pd.client_id, pd.month_count
FROM project_durations pd
         JOIN max_duration md ON pd.month_count = md.max_months
ORDER BY pd.client_id;
