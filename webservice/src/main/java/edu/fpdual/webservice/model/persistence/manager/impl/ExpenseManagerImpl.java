package edu.fpdual.webservice.model.persistence.manager.impl;


import edu.fpdual.webservice.model.persistence.dao.Expense;
import edu.fpdual.webservice.model.persistence.manager.ExpenseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManagerImpl implements ExpenseManager {
    final String tableName = "EXPENSE";
    @Override
    public List<Expense> findAll(Connection con) {
        List<Expense> entities = new ArrayList<>();
        String query = "SELECT * FROM " +tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while(result.next()) {
                entities.add(new Expense(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<Expense> findAllBy(Connection con, String fieldName, Object value) {
        List<Expense> entities = new ArrayList<>();
        String query = "SELECT * FROM " +tableName +" WHERE " +fieldName +" = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entities.add(new Expense(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }

        return entities;
    }

    @Override
    public Expense findBy(Connection con, String fieldName, Object value) {
        Expense entity = new Expense();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                entity = new Expense(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }

        return entity;
    }

    @Override
    public boolean delete(Connection con, Expense expense) {
        boolean result;

        String query = "DELETE FROM " + tableName + " WHERE expenseId = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, expense.getExpenseId());
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean create(Connection con, Expense expense) {
        boolean result;

        String query = "INSERT INTO " + tableName + "(budgetId, expenseName, description, categoryId, amount, isRecurrent) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setInt(1, expense.getBudgetId());
            stm.setString(2, expense.getExpenseName());
            stm.setString(3, expense.getDescription());
            stm.setInt(4, expense.getCategoryId());
            stm.setDouble(5, expense.getAmount());
            stm.setBoolean(6, expense.isRecurrent());

            result = stm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Connection con, Expense expense) {
        boolean  result;
        String query = "UPDATE " +tableName + " SET expenseName = ?, description = ?, categoryId = ?, amount = ?, isRecurrent = ? WHERE expenseId = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, expense.getExpenseName());
            stm.setString(2, expense.getDescription());
            stm.setInt(3, expense.getCategoryId());
            stm.setDouble(4, expense.getAmount());
            stm.setBoolean(5, expense.isRecurrent());
            stm.setInt(6, expense.getExpenseId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
