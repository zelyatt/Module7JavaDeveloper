package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
       try {
           String DB_URL = "jdbc:h2:tcp://localhost/~/test";
           String DB_USER = "sa";
           String DB_PASSWORD = "";
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
    public static Database getInstance() {
        if(instance == null){
            instance = new Database();

        } return instance;
    }
    public Connection getConnection(){
        return connection;
    }




}
