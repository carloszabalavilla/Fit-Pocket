package edu.fpdual.webservice.model.persistence.manager.impl;

import edu.fpdual.webservice.model.persistence.dao.Income;
import edu.fpdual.webservice.model.persistence.manager.IncomeManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeManagerImpl implements IncomeManager {
    final String tableName = "INCOME";

    @Override
    public List<Income> findAll(Connection con) {
        List<Income> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new Income(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<Income> findAllBy(Connection con, String fieldName, Object value) {
        List<Income> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entities.add(new Income(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }

        return entities;
    }

    @Override
    public Income findBy(Connection con, String fieldName, Object value) {
        Income entity = new Income();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entity = new Income(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }

        return entity;
    }

    @Override
    public boolean delete(Connection con, Income income) {
        boolean result;

        String query = "DELETE FROM " + tableName + " WHERE incomeId = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, income.getIncomeId());
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean create(Connection con, Income income) {
        boolean result;

        String query = "INSERT INTO " + tableName + "(budgetId, incomeName, description, categoryId, amount, isRecurrent) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setInt(1, income.getBudgetId());
            stm.setString(2, income.getIncomeName());
            stm.setString(3, income.getDescription());
            stm.setInt(4, income.getCategoryId());
            stm.setDouble(5, income.getAmount());
            stm.setBoolean(6, income.isRecurrent());

            result = stm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Connection con, Income income) {
        boolean result;
        String query = "UPDATE " + tableName + " SET incomeName = ?, description = ?, categoryId = ?, amount = ?, isRecurrent = ? WHERE incomeId = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, income.getIncomeName());
            stm.setString(2, income.getDescription());
            stm.setInt(3, income.getCategoryId());
            stm.setDouble(4, income.getAmount());
            stm.setBoolean(5, income.isRecurrent());
            stm.setInt(6, income.getIncomeId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
