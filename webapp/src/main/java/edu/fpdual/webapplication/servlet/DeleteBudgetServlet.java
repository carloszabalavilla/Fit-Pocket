package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.dto.Budget;
import edu.fpdual.webapplication.service.BudgetService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteBudgetServlet", urlPatterns = {"/delete-budget-servlet"})
public class DeleteBudgetServlet extends HttpServlet {
    private BudgetService budgetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        budgetService = new BudgetService(new BudgetClient());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ok = "Presupuesto Borrado.";
        String error = "No se ha podido borrar el presupuesto.";

        int budgetId = Integer.parseInt(request.getParameter("deleteBtn"));
        Budget budget = budgetService.getBudgetById(budgetId);

        if (new BudgetService(new BudgetClient()).deleteBudget(budget)) {
            request.setAttribute("ok", ok);
            response.sendRedirect(GlobalInfo.URL_JSP_SUCCESS);
        } else {
            System.out.println("Else error");
            request.setAttribute("error", error);
            request.getRequestDispatcher(GlobalInfo.URL_JSP_LISTBUDGET).forward(request, response);
        }
    }
}
