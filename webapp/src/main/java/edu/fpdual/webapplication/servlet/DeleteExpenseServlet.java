package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.ExpenseClient;
import edu.fpdual.webapplication.dto.Expense;
import edu.fpdual.webapplication.service.ExpenseService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteExpenseServlet", urlPatterns = {"/delete-expense-servlet"})
public class DeleteExpenseServlet extends HttpServlet {
    private ExpenseService expenseService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        expenseService = new ExpenseService(new ExpenseClient());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ok = "Gasto Borrado.";
        String error = "No se ha podido borrar el gasto.";

        int expenseId = Integer.parseInt(request.getParameter("deleteBtnExpense"));
        Expense expense = expenseService.getExpenseById(expenseId);

        if (new ExpenseService(new ExpenseClient()).deleteExpense(expense)) {
            request.setAttribute("ok", ok);
            response.sendRedirect(GlobalInfo.URL_JSP_SUCCESS);
        } else {
            System.out.println("Else error");
            request.setAttribute("error", error);
            request.getRequestDispatcher(GlobalInfo.URL_JSP_BUDGETDATA).forward(request, response);
        }
    }
}
