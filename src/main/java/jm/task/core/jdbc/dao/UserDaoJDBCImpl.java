package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
        // TODO document why this constructor is empty
    }

    public void createUsersTable() {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(10), lastName VARCHAR(10), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement =  Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("INSERT INTO USERS VALUES (DEFAULT, '" + name + "', '" +
                    lastName + "', " + age + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public LinkedList<User> removeUserById(long id) {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM USERS WHERE ID = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM USERS");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}