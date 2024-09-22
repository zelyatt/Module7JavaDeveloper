package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        // Отримання з'єднання через клас Database
        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {


            String sql = new String(Files.readAllBytes(Paths.get("sql/init_db.sql")));

            stmt.execute(sql);
            System.out.println("База даних успішно проініціалізована.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

