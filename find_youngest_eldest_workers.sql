WITH age_data AS (
    SELECT
        name,
        date_of_birth,
        CASE
            WHEN date_of_birth = (SELECT MIN(date_of_birth) FROM workers) THEN 'ELDEST'
            WHEN date_of_birth = (SELECT MAX(date_of_birth) FROM workers) THEN 'YOUNGEST'
            END AS type
    FROM workers
)

SELECT
    type,
    name,
    date_of_birth AS birthday
FROM age_data
WHERE type IS NOT NULL
ORDER BY type, name;
