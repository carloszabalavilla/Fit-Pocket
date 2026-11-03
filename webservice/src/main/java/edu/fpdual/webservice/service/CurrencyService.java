package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Currency;
import edu.fpdual.webservice.model.persistence.manager.CurrencyManager;
import edu.fpdual.webservice.model.persistence.manager.impl.CurrencyManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CurrencyService {
    private final CurrencyManager currencyManager;

    public CurrencyService(CurrencyManagerImpl currencyManager) {
        this.currencyManager = currencyManager;
    }

    public List<Currency> findAllCurrencies() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return currencyManager.findAll(con);
        }
    }

    public Currency findByCurrencyName(String currencyName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return currencyManager.findBy(con, "currencyName", currencyName);
        }
    }

    public boolean createCurrency(Currency currency) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return currencyManager.create(con, currency);
        }
    }

    public boolean updateCurrency(Currency currency) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return currencyManager.update(con, currency);
        }
    }

    public boolean deleteCurrency(Currency currency) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return currencyManager.delete(con, currency);
        }
    }
}
