package edu.fpdual.webservice.model.persistence.manager.impl;


import edu.fpdual.webservice.model.persistence.dao.Currency;
import edu.fpdual.webservice.model.persistence.manager.CurrencyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyManagerImpl implements CurrencyManager {
    final String tableName = "CURRENCY";

    @Override
    public List<Currency> findAll(Connection con) {

        List<Currency> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new Currency(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }

        return entities;
    }

    @Override
    public List<Currency> findAllBy(Connection con, String fieldName, Object value) {
        List<Currency> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entities.add(new Currency(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public Currency findBy(Connection con, String fieldName, Object value) {
        Currency entity = new Currency();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery(query);

            while (result.next()) {
                entity = new Currency(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }

        return entity;
    }

    @Override
    public boolean delete(Connection con, Currency currency) {
        boolean result;

        String query = "DELETE FROM " + tableName + " WHERE budgetName = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, currency.getCurrencyName());
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean create(Connection con, Currency currency) {
        boolean result;
        String query = "INSERT INTO " + tableName + " (currencyName, currencySymbol) values (?, ?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, currency.getCurrencyName());
            stm.setString(2, currency.getCurrencySymbol());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Connection con, Currency currency) {
        boolean result;
        String query = "UPDATE " + tableName + " SET currencyName = ?, currencySymbol = ? WHERE currencyId = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setString(1, currency.getCurrencyName());
            stm.setString(2, currency.getCurrencySymbol());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
