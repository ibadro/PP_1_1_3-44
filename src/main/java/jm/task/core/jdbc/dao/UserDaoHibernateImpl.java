package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory SessionFactory;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {
        this.SessionFactory = Util.sessionFactory();
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(20) NOT NULL, " +
                " lastName VARCHAR(20) NOT NULL, " +
                " age INT NOT NULL, " +
                " PRIMARY KEY (id))";

        try (Session session = SessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("INFO: Table created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Session session = SessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("INFO: Table deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = SessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("INFO: Remove successful, user id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = SessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User");
            System.out.println("INFO: Getting Users successfully");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (Session session = SessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("INFO: Data Delete successful!");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}