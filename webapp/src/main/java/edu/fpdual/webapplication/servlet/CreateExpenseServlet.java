package edu.fpdual.webapplication.servlet;


import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.CategoryClient;
import edu.fpdual.webapplication.client.ExpenseClient;
import edu.fpdual.webapplication.dto.Category;
import edu.fpdual.webapplication.dto.Expense;
import edu.fpdual.webapplication.service.CategoryService;
import edu.fpdual.webapplication.service.ExpenseService;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateExpenseServlet", urlPatterns = {"/create-expense-servlet"})
public class CreateExpenseServlet extends HttpServlet {
    private CategoryService categoryService;
    private int budgetId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryService = new CategoryService(new CategoryClient());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
        List<Category> categoryList = categoryService.getAllCategoriesByUserId(0);
        categoryList.addAll(categoryService.getAllCategoriesByUserId(session.getUserId()));
        request.getSession().setAttribute("categoryList", categoryList);

        budgetId = Integer.parseInt(request.getParameter("expenseBtn"));

        response.sendRedirect(GlobalInfo.URL_JSP_ADDEXPENSE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ok = "Gasto Creado.";
        String error = "No se ha podido crear el gasto.";

        Expense expense;

        try {
            Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);

            if (session != null && budgetId > 0) {

                expense = Expense.builder()
                        .budgetId(budgetId)
                        .expenseName(request.getParameter("expenseNameInput"))
                        .description(request.getParameter("expenseDescInput"))
                        .categoryId(Integer.parseInt(request.getParameter("categoryInput")))
                        .amount(Double.parseDouble(request.getParameter("expenseAmountInput")))
                        .isRecurrent(Boolean.parseBoolean(request.getParameter("recurrentExpense")))
                        .build();

                if (new ExpenseService(new ExpenseClient()).createExpense(expense)) {
                    request.setAttribute("ok", ok);
                    response.sendRedirect((GlobalInfo.URL_JSP_SUCCESS));
                } else {
                    request.setAttribute("error", error);
                    request.getRequestDispatcher(GlobalInfo.URL_JSP_ADDINCOME).forward(request, response);
                }
            }
        } catch (ServletException e) {
            request.setAttribute("error", error);
            e.printStackTrace();
        }
    }
}
