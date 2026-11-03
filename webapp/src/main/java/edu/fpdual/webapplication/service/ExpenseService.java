package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.ExpenseClient;
import edu.fpdual.webapplication.dto.Expense;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class ExpenseService {

    private final ExpenseClient expenseClient;

    public ExpenseService(ExpenseClient expenseClient) {
        this.expenseClient = expenseClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return expenseClient.ping();
    }

    public Expense getExpense(String expenseName) {
        return expenseClient.get(expenseName);
    }

    public Expense getExpenseById(int expenseId) {
        return expenseClient.getById(expenseId);
    }

    public List<Expense> getAllExpenses() {
        return expenseClient.get();
    }

    public boolean updateExpense(Expense expense) {
        return expenseClient.put(expense);
    }

    public boolean createExpense(Expense expense) {
        return expenseClient.post(expense);
    }

    public boolean deleteExpense(Expense expense) {
        return expenseClient.delete(expense);
    }

}
