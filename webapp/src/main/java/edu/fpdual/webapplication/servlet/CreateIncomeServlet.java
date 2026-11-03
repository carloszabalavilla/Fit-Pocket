package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.CategoryClient;
import edu.fpdual.webapplication.client.IncomeClient;
import edu.fpdual.webapplication.dto.Category;
import edu.fpdual.webapplication.dto.Income;
import edu.fpdual.webapplication.service.CategoryService;
import edu.fpdual.webapplication.service.IncomeService;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateIncomeServlet", urlPatterns = {"/create-income-servlet"})
public class CreateIncomeServlet extends HttpServlet {
    private CategoryService categoryService;
    private int budgetId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryService = new CategoryService(new CategoryClient());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
        List<Category> categoryList = categoryService.getAllCategoriesByUserId(0);
        categoryList.addAll(categoryService.getAllCategoriesByUserId(session.getUserId()));
        request.getSession().setAttribute("categoryList", categoryList);

        budgetId = Integer.parseInt(request.getParameter("incomeBtn"));

        response.sendRedirect(GlobalInfo.URL_JSP_ADDINCOME);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String ok = "Ingreso Creado.";
        String error = "No se ha podido crear el presupuesto.";

        Income income;

        try {
            Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);

            if (session != null && budgetId > 0) {
                income = Income.builder()
                        .budgetId(budgetId)
                        .incomeName(request.getParameter("incomeNameInput"))
                        .description(request.getParameter("incomeDescInput"))
                        .categoryId(Integer.parseInt(request.getParameter("categoryInput")))
                        .amount(Double.parseDouble(request.getParameter("incomeAmountInput")))
                        .isRecurrent(Boolean.parseBoolean(request.getParameter("recurrentIncome")))
                        .build();

                if (new IncomeService(new IncomeClient()).createIncome(income)) {
                    request.setAttribute("ok", ok);
                    response.sendRedirect(GlobalInfo.URL_JSP_SUCCESS);
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
