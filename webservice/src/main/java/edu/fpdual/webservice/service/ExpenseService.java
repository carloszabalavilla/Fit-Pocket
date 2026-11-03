package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Expense;
import edu.fpdual.webservice.model.persistence.manager.ExpenseManager;
import edu.fpdual.webservice.model.persistence.manager.impl.ExpenseManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExpenseService {
    private final ExpenseManager expenseManager;

    public ExpenseService(ExpenseManagerImpl expenseManager) {

        this.expenseManager = expenseManager;
    }

    public List<Expense> findAllExpenses() throws SQLException, ClassNotFoundException{
        try(Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.findAll(con);
        }
    }

    public List<Expense> findAllExpensesBy(String field, int value) throws SQLException, ClassNotFoundException{
        try(Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.findAllBy(con,field,value);
        }
    }

    public Expense findByExpenseDate(LocalDate expenseDate) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.findBy(con, "incomeDate", expenseDate);
        }
    }

    public Expense findByExpenseName(String expenseName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.findBy(con, "expenseName", expenseName);
        }
    }

    public Expense findByExpenseId(int expenseId) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.findBy(con, "expenseId", expenseId);
        }
    }

    public boolean deleteExpense(Expense expense) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.delete(con, expense);
        }
    }
    public boolean createExpense(Expense expense) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.create(con, expense);
        }
    }
    public boolean updateExpense(Expense expense) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return expenseManager.update(con, expense);
        }
    }

}
