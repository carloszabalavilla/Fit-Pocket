package edu.fpdual.webservice.model.persistence.manager.impl;

import edu.fpdual.webservice.model.persistence.dao.Budget;
import edu.fpdual.webservice.model.persistence.manager.BudgetManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetManagerImpl implements BudgetManager {
    final String tableName = "BUDGET";

    @Override
    public List<Budget> findAll(Connection con) {
        List<Budget> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new Budget(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<Budget> findAllBy(Connection con, String fieldName, Object value) {
        List<Budget> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ? ";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entities.add(new Budget(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public Budget findBy(Connection con, String fieldName, Object value) {
        Budget entity = new Budget();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            if (value.getClass().equals(String.class)) {
                value = ((String) value).toLowerCase();
            }

            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entity = new Budget(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean delete(Connection con, Budget budget) {
        boolean result;

        String query = "DELETE FROM " + tableName + " WHERE budgetId = ? ";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, budget.getBudgetId());
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean create(Connection con, Budget budget) {
        boolean result;
        String query = "INSERT INTO " + tableName + " (userId, budgetName, description, currencyId) values (?, ?, ?, ?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setInt(1, budget.getUserId());
            stm.setString(2, budget.getBudgetName());
            stm.setString(3, budget.getDescription());
            stm.setInt(4, budget.getCurrencyId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Connection con, Budget budget) {
        boolean result;
        String query = "UPDATE " + tableName + " SET budgetName = ?, description = ?, currencyId = ? WHERE budgetId = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, budget.getBudgetName());
            stm.setString(2, budget.getDescription());
            stm.setInt(3, budget.getCurrencyId());
            stm.setInt(4, budget.getBudgetId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
