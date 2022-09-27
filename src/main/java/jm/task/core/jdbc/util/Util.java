package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mysql";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void setConnectionClose() throws SQLException {

            connection.close();
            System.out.println("Подключение успешно закрыто");

    }


}


