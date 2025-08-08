package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute(createTable);
        } catch (SQLException e) {

        }
    }

    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute(dropTable);
        } catch (SQLException e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = Util.getConnection().prepareStatement(saveUser);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            //Statement statement = Util.getConnection().createStatement();
            //statement.execute(saveUser);
        } catch (SQLException e) {

        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statement = Util.getConnection().prepareStatement(removeUser);
            statement.setLong(1, id);
            statement.executeUpdate();
            //Statement statement = Util.getConnection().createStatement();
            //statement.execute(removeUser);
        } catch (SQLException e) {

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String query = "SELECT id, name, lastName, age FROM users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {

        }
    }
}
