package ru.job4j.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (FileInputStream fileInputStream = new FileInputStream(
                "chapter_003_-_database/src/main/resources/app.properties")) {
            Properties property = new Properties();
            property.load(fileInputStream);
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(
                    property.getProperty("db.host"),
                    property.getProperty("db.login"),
                    property.getProperty("db.password"))) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
}
