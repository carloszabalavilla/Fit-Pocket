package edu.fpdual.webapplication.servlet;


import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.BudgetClient;
import edu.fpdual.webapplication.client.CurrencyClient;
import edu.fpdual.webapplication.dto.Budget;
import edu.fpdual.webapplication.dto.Currency;
import edu.fpdual.webapplication.service.BudgetService;
import edu.fpdual.webapplication.service.CurrencyService;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "CreateBudgetServlet", urlPatterns = {"/create-budget-servlet"})
public class CreateBudgetServlet extends HttpServlet {
    private CurrencyService currencyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        currencyService = new CurrencyService(new CurrencyClient());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Currency> currencyList = currencyService.getAllCurrency();
        request.getSession().setAttribute("currencyList", currencyList);
        response.sendRedirect("/ProyectoPrueba/jsp/common/addBudget.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ok = "Presupuesto Creado.";
        String error = "No se ha podido crear el presupuesto.";

        Budget budget;

        try {
            Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
            int userIdSession = session.getUserId();
            String nombreIntroducido = request.getParameter("budgetNameInput");

            if (nombreIntroducido != null) {

                budget = Budget.builder()
                        .userId(userIdSession)
                        .budgetName(request.getParameter("budgetNameInput"))
                        .description(request.getParameter("budgetDescInput"))
                        .currencyId(Integer.parseInt(request.getParameter("currencyInput")))
                        .build();

                if (new BudgetService(new BudgetClient()).createBudget(budget)) {
                    request.setAttribute("ok", ok);
                    response.sendRedirect(GlobalInfo.URL_JSP_SUCCESS);
                } else {
                    System.out.println("Else error");
                    request.setAttribute("error", error);
                    request.getRequestDispatcher(GlobalInfo.URL_JSP_ADDBUDGET).forward(request, response);

                }
            }

        } catch (ServletException e) {
            request.setAttribute("error", error);
            e.printStackTrace();
        }
    }
}
