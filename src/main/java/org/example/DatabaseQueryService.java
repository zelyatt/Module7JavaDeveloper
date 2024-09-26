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

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get("sql/find_max_projects_client.sql")));
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                int projectCount = rs.getInt("project_count");
                result.add(new MaxProjectCountClient(name, projectCount));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<LongestProjects> findMaxProjectDurationClients() {
        List<LongestProjects> result = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("sql/find_longest_project.sql")));
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int clientId = rs.getInt("client_id");
                int monthCount = rs.getInt("month_count");

                result.add(new LongestProjects(clientId, monthCount));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        if (result.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        }

        return result;
    }
    public List<MaxSalary> findMaxSalary() {
        List<MaxSalary> result = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("sql/find_max_salary_worker.sql")));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString("NAME");
                int salary = rs.getInt("SALARY");
                result.add(new MaxSalary(name, salary));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }return result;
    }
    public List<YoungestAndOldestWorker> findYoungestAndOldestWorker() {
        List<YoungestAndOldestWorker> result = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get("sql/find_youngest_eldest_workers.sql")));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString("NAME");
                Date dateOfBirth = rs.getDate("DATE_OF_BIRTH");
                String type = rs.getString("TYPE");

                result.add(new YoungestAndOldestWorker(name, dateOfBirth, type));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return result;
    }
}




