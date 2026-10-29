package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.dto.Budget;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class BudgetService {

    private final BudgetClient budgetClient;


    public BudgetService(BudgetClient budgetClient) {
        this.budgetClient = budgetClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online".
     */
    public String ping() {
        return budgetClient.ping();
    }

    /**
     * Find a budget by the name.
     *
     * @param budgetName the name.
     * @return a {@link Budget}.
     */
    public Budget getBudgetByName(String budgetName) {
        return budgetClient.get(budgetName);
    }

    /**
     * Find a budget by the id.
     *
     * @param budgetId the id.
     * @return a {@link Budget}.
     */
    public Budget getBudgetById(int budgetId) {
        return budgetClient.get(budgetId);
    }

    /**
     * Find all budgets existing.
     *
     * @return a {@link List} of {@link Budget}.
     */
    public List<Budget> getAllBudgets() {
        return budgetClient.get();
    }

    /**
     * Find all budgets existing by the user id.
     *
     * @param userId the id.
     * @return a {@link List} of {@link Budget}.
     */
    public List<Budget> getAllBudgetsByUserId(int userId) {
        return budgetClient.getAllBy(userId);
    }

    /**
     * Update a budget information.
     *
     * @param budget the budget to update.
     * @return a boolean.
     */
    public boolean updateBudget(Budget budget) {
        return budgetClient.put(budget);
    }

    /**
     * Create a new budget.
     *
     * @param budget the budget to create.
     * @return a boolean.
     */
    public boolean createBudget(Budget budget) {
        return budgetClient.post(budget);
    }

    /**
     * Delete a budget.
     *
     * @param budget the budget to delete.
     * @return a boolean.
     */
    public boolean deleteBudget(Budget budget) {
        return budgetClient.delete(budget);
    }

}
