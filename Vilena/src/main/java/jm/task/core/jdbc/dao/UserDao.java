package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable();  // Создание таблицы для User(ов)

    void dropUsersTable();  // Удаление таблицы User(ов)

    void saveUser(String name, String lastName, byte age) throws SQLException; // Добавление User в таблицу

    void removeUserById(long id) throws SQLException;  // Удаление User из таблицы (по id)

    List<User> getAllUsers() throws SQLException;  // Получение всех User(ов) из таблицы

    void cleanUsersTable();  // Очистка содержания таблицы
}
