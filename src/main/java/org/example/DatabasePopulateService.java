package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get("sql/populate_db.sql")));

            stmt.execute(sql);
            System.out.println("Таблиці бази даних успішно наповнені.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

