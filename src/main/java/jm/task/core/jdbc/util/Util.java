package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");

        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
        return connection;
    }

    public static void ConnectionClose() {
        try {
            connection.close();
            System.out.println("Подключение успешно закрыто");
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }

    }
}

