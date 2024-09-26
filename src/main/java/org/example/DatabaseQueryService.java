package org.example;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        String sql = "WITH project_durations AS ( " +
                "    SELECT id, client_id, " +
                "           DATEDIFF('MONTH', start_date, end_date) AS month_count " +
                "    FROM projects " +
                "), " +
                "max_duration AS ( " +
                "    SELECT MAX(month_count) AS max_months " +
                "    FROM project_durations " +
                ") " +
                "SELECT pd.client_id, pd.month_count " +
                "FROM project_durations pd " +
                "JOIN max_duration md ON pd.month_count = md.max_months " +
                "ORDER BY pd.client_id;";

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int clientId = rs.getInt("client_id");
                int monthCount = rs.getInt("month_count");
                result.add(new MaxProjectCountClient(clientId, monthCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        }

        return result;
    }


    public List<LongestProjects> findMaxProjectDurationClients() {
        List<LongestProjects> result = new ArrayList<>();

        String sql = "WITH worker_project_counts AS ( " +
                "    SELECT ep.worker_id, COUNT(DISTINCT ep.project_id) AS project_count " +
                "    FROM employee_projects ep " +
                "    GROUP BY ep.worker_id " +
                "), " +
                "max_projects AS ( " +
                "    SELECT MAX(project_count) AS max_count " +
                "    FROM worker_project_counts " +
                ") " +
                "SELECT w.id, w.name, wpc.project_count " +
                "FROM workers w " +
                "JOIN worker_project_counts wpc ON w.id = wpc.worker_id " +
                "JOIN max_projects mp ON wpc.project_count = mp.max_count " +
                "ORDER BY w.id;";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int clientId = rs.getInt("id");
                int projectCount = rs.getInt("project_count");

                result.add(new LongestProjects(clientId, projectCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        }

        return result;
    }

    public List<MaxSalary> findMaxSalary() {
        List<MaxSalary> result = new ArrayList<>();
        String sql = "SELECT name, salary FROM workers ORDER BY salary DESC LIMIT 1";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                result.add(new MaxSalary(name, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<YoungestAndOldestWorker> findYoungestAndOldestWorker() {
        List<YoungestAndOldestWorker> result = new ArrayList<>();

        String minMaxQuery = "SELECT MIN(date_of_birth) AS minDate, MAX(date_of_birth) AS maxDate FROM workers";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement minMaxStatement = connection.prepareStatement(minMaxQuery);
             ResultSet minMaxRs = minMaxStatement.executeQuery()) {

            Date minDate = null;
            Date maxDate = null;

            if (minMaxRs.next()) {
                minDate = minMaxRs.getDate("minDate");
                maxDate = minMaxRs.getDate("maxDate");
            }

            if (minDate != null && maxDate != null) {
                String workerQuery = "SELECT name, date_of_birth, " +
                        "CASE WHEN date_of_birth = ? THEN 'ELDEST' " +
                        "     WHEN date_of_birth = ? THEN 'YOUNGEST' END AS type " +
                        "FROM workers " +
                        "WHERE date_of_birth = ? OR date_of_birth = ?";

                try (PreparedStatement workerStatement = connection.prepareStatement(workerQuery)) {
                    // Установка параметров для старшей и младшей даты
                    workerStatement.setDate(1, minDate);
                    workerStatement.setDate(2, maxDate);
                    workerStatement.setDate(3, minDate);
                    workerStatement.setDate(4, maxDate);

                    ResultSet workerRs = workerStatement.executeQuery();
                    while (workerRs.next()) {
                        String name = workerRs.getString("name");
                        Date dateOfBirth = workerRs.getDate("date_of_birth");
                        String type = workerRs.getString("type");

                        if (type != null) {
                            result.add(new YoungestAndOldestWorker(name, dateOfBirth, type));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}




