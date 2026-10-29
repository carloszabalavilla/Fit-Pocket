package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Budget;
import edu.fpdual.webservice.model.persistence.manager.BudgetManager;
import edu.fpdual.webservice.model.persistence.manager.impl.BudgetManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BudgetService {
    private final BudgetManager budgetManager;

    public BudgetService(BudgetManagerImpl budgetManager) {

        this.budgetManager = budgetManager;
    }

    public List<Budget> findAllBudgets() throws SQLException, ClassNotFoundException{
        try(Connection con = new MySQLConnector().getMySQLConnection()) {
            return enrichAll(con, budgetManager.findAll(con));
        }
    }

    public List<Budget> findAllBudgetsByUserId(int userId) throws SQLException, ClassNotFoundException{
        try(Connection con = new MySQLConnector().getMySQLConnection()) {
            return enrichAll(con, budgetManager.findAllBy(con, "userId", userId));
        }
    }

    public Budget findByBudgetDate(LocalDate budgetDate) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return enrich(con, budgetManager.findBy(con, "budgetDate", budgetDate));
        }
    }

    public Budget findByBudgetName(String budgetName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return enrich(con, budgetManager.findBy(con, "budgetName", budgetName));
        }
    }
    public Budget findByBudgetId(int budgetId) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return enrich(con, budgetManager.findBy(con, "budgetId", budgetId));
        }
    }
    public boolean deleteBudget(Budget budget) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return budgetManager.delete(con, budget);
        }
    }
    public boolean createBudget(Budget budget) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return budgetManager.create(con, budget);
        }
    }
    public boolean updateBudget(Budget budget) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return budgetManager.update(con, budget);
        }
    }


    private Budget enrich(Connection con, Budget budget) {
        if (budget != null) {
            budget.setIncomeList(new edu.fpdual.webservice.model.persistence.manager.impl.IncomeManagerImpl().findAllBy(con, "budgetId", budget.getBudgetId()));
            budget.setExpenseList(new edu.fpdual.webservice.model.persistence.manager.impl.ExpenseManagerImpl().findAllBy(con, "budgetId", budget.getBudgetId()));
        }
        return budget;
    }
    private List<Budget> enrichAll(Connection con, List<Budget> budgets) {
        if (budgets != null) budgets.forEach(budget -> enrich(con, budget));
        return budgets;
    }
}
