package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String userName = "root";
    private static String password = "naroot";
    private static String connectionUrl = "jdbc:mysql://localhost:3306/pp_1_1_3_jdbc";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }

    }
}


