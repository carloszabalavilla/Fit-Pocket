package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.dto.Budget;
import edu.fpdual.webapplication.service.BudgetService;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListBudgetServlet", urlPatterns = {"/list-budget-servlet"})
public class ListBudgetServlet extends HttpServlet {
    private BudgetService budgetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        budgetService = new BudgetService(new BudgetClient());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
        List<Budget> budgetList = budgetService.getAllBudgetsByUserId(session.getUserId());
        request.getSession().setAttribute("budgetList", budgetList);
        response.sendRedirect(GlobalInfo.URL_JSP_LISTBUDGET);
    }

}
