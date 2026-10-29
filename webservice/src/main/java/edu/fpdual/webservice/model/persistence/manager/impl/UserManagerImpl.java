package edu.fpdual.webservice.model.persistence.manager.impl;


import edu.fpdual.webservice.model.persistence.dao.User;
import edu.fpdual.webservice.model.persistence.manager.UserManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagerImpl implements UserManager {
    final String tableName = "USER";

    //@TODO AÑADIR JAVADOC

    public List<User> findAll(Connection con) {
        List<User> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new User(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<User> findAllBy(Connection con, String fieldName, Object value) {
        List<User> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";
        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entities.add(new User(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public User findBy(Connection con, String fieldName, Object value) {
        User entity = new User();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            if (value.getClass().equals(String.class)) {
                value = ((String) value).toLowerCase();
            }
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entity = new User(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    @Override
    public boolean delete(Connection con, User entity) {
        boolean result;
        String query = "DELETE FROM " + tableName + " WHERE userName" + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, entity.getUserName().toLowerCase());
            result = stm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean create(Connection con, User entity) {
        boolean result;
        String query = "INSERT INTO " + tableName + " (userName, userPassword, isAdmin) values(?,?,?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, entity.getUserName().toLowerCase());
            stm.setString(2, entity.getUserPassword());
            stm.setInt(3, entity.isAdmin() ? 1 : 0);

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     *
     */
    @Override
    public boolean update(Connection con, User entity) {
        boolean result;
        String query = "UPDATE " + tableName + " SET userName = ? , userPassword = ? , isAdmin = ? WHERE userId = ?";
        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, entity.getUserName());
            stm.setString(2, entity.getUserPassword());
            stm.setInt(3, entity.isAdmin() ? 1 : 0);
            stm.setInt(4, entity.getUserId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
