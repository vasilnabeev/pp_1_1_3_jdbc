package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static String userName = "root";
    private static String password = "naroot";
    private static String connectionUrl = "jdbc:mysql://localhost:3306/pp_1_1_3_jdbc";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Настройки Hibernate
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, connectionUrl);
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}


