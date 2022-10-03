package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import javax.transaction.SystemException;
import java.util.LinkedList;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SystemException;

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
