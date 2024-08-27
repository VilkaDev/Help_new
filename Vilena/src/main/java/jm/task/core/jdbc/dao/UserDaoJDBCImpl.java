package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "CREATE TABLE if not exists Users (id int auto_increment not null, name text not null, lastname text not null, age int null, constraint users_pk primary key (id));";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Create UsersTable need fix");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "DROP TABLE if exists Users";
            statement.executeUpdate(sql);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            System.out.println("Drop UsersTable need fix");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO Users VALUES (id, ?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("%s добавлен в базу данных", name);

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении User в базу данных");
        }
    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User users = new User();
                users.setId(resultSet.getLong("id"));
                users.setName(resultSet.getString("name"));
                users.setLastName(resultSet.getString("lastname"));
                users.setAge(resultSet.getByte("age"));
                userList.add(users);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "TRUNCATE Users";
            statement.executeUpdate(sql);
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            System.out.println("Create UsersTable need fix");
        }
    }
}
