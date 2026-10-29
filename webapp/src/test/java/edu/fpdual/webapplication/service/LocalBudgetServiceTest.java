package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.dto.Budget;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/** Exercises the service without depending on a server or historical database. */
class LocalBudgetServiceTest {
    @Test void serviceHandlesEmptyCatalogAndRejectedWrites() {
        BudgetClient client = new BudgetClient() {
            @Override public List<Budget> get() { return List.of(); }
            @Override public boolean post(Budget budget) { return false; }
        };
        BudgetService service = new BudgetService(client);
        assertTrue(service.getAllBudgets().isEmpty());
        assertFalse(service.createBudget(new Budget()));
    }
}
