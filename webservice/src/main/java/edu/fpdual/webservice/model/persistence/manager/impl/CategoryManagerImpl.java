package edu.fpdual.webservice.model.persistence.manager.impl;

import edu.fpdual.webservice.model.persistence.dao.Category;
import edu.fpdual.webservice.model.persistence.manager.CategoryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager {
    final String tableName = "CATEGORY";

    @Override
    public List<Category> findAll(Connection con) {
        List<Category> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new Category(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<Category> findAllBy(Connection con, String fieldName, Object value) {
        return null;
    }

    @Override
    public List<Category> findAllById(Connection con, String fieldName, int value) {
        List<Category> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setInt(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entities.add(new Category(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public Category findBy(Connection con, String fieldName, Object value) {
        Category entity = new Category();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            if (value.getClass().equals(String.class)) {
                value = ((String) value).toLowerCase();
            }
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entity = new Category(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    @Override
    public boolean delete(Connection con, Category entity) {
        boolean result;
        String query = "DELETE FROM " + tableName + " WHERE categoryId = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, entity.getCategoryId());
            result = stm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Cuando creas un usuario, el return del método debe de ser 4.
     */
    @Override
    public boolean create(Connection con, Category entity) {
        boolean result;
        String query = "INSERT INTO " + tableName + " (categoryName) values (?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, entity.getCategoryName());
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
    public boolean update(Connection con, Category entity) {
        boolean result;
        String query = "UPDATE " + tableName + " SET categoryName = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, entity.getCategoryName());
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
