package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.client.*;
import edu.fpdual.webapplication.service.*;
import edu.fpdual.webapplication.service.wrapper.ServiceWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PingListServlet", urlPatterns = {"/ping-list-servlet"})
public class PingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherURLPingList = "jsp/admin/database/pingList.jsp";

        List<ServiceWrapper<?>> serviceList = getServiceList();

        for (ServiceWrapper<?> serviceWrapper : serviceList) {
            try {
                String serviceName = serviceWrapper.getServiceName();
                Object service = serviceWrapper.getService();

                Method pingMethod = service.getClass().getMethod("ping");
                String result = (String) pingMethod.invoke(service);

                request.setAttribute(serviceName, result);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                request.setAttribute(serviceWrapper.getServiceName(), "Este servicio no está disponible.");
            }
        }
        request.getRequestDispatcher(dispatcherURLPingList).forward(request, response);
    }

    private List<ServiceWrapper<?>> getServiceList() {
        List<ServiceWrapper<?>> serviceList = new ArrayList<>();
        serviceList.add(new ServiceWrapper<>("Budget", new BudgetService(new BudgetClient())));
        serviceList.add(new ServiceWrapper<>("Category", new CategoryService(new CategoryClient())));
        serviceList.add(new ServiceWrapper<>("Expense", new ExpenseService(new ExpenseClient())));
        serviceList.add(new ServiceWrapper<>("Income", new IncomeService(new IncomeClient())));
        serviceList.add(new ServiceWrapper<>("Profile", new ProfileService(new ProfileClient())));
        serviceList.add(new ServiceWrapper<>("Report", new ReportService(new ReportClient())));
        serviceList.add(new ServiceWrapper<>("User", new UserService(new UserClient())));
        return serviceList;
    }
}
