package edu.fpdual.webapplication.service;


import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.IncomeClient;
import edu.fpdual.webapplication.dto.Income;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class IncomeService {

    private final IncomeClient incomeClient;

    public IncomeService(IncomeClient incomeClient) {
        this.incomeClient = incomeClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return incomeClient.ping();
    }

    public Income getIncome(String incomeName) {
        return incomeClient.get(incomeName);
    }

    public Income getIncomeById(int incomeId) {
        return incomeClient.getById(incomeId);
    }

    public List<Income> getAllIncomes() {
        return incomeClient.get();
    }

    public boolean updateIncome(Income income) {
        return incomeClient.put(income);
    }

    public boolean createIncome(Income income) {
        return incomeClient.post(income);
    }

    public boolean deleteIncome(Income income) {
        return incomeClient.delete(income);
    }

}
