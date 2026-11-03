package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.dto.Budget;
import edu.fpdual.webapplication.dto.Expense;
import edu.fpdual.webapplication.dto.Income;
import edu.fpdual.webapplication.service.BudgetService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BudgetDataServlet", urlPatterns = {"/see-budget"})
public class BudgetDataServlet extends HttpServlet {
    private BudgetService budgetService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        budgetService = new BudgetService(new BudgetClient());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int budgetId = Integer.parseInt(request.getParameter("submitBtn"));
        Budget budget = budgetService.getBudgetById(budgetId);
        request.getSession().setAttribute("budget", budget);

        double balance = 0;

        if (budget.getIncomeList().size() > 0 || budget.getExpenseList().size() > 0) {
            double sumaIncome = 0;
            for (Income inc : budget.getIncomeList()) {
                sumaIncome += inc.getAmount();
            }
            double sumaExpense = 0;
            for (Expense exp : budget.getExpenseList()) {
                sumaExpense += exp.getAmount();
            }
            balance = sumaIncome - sumaExpense;
        }

        request.getSession().setAttribute("balance", balance);

        response.sendRedirect(GlobalInfo.URL_JSP_BUDGETDATA);
    }

}
