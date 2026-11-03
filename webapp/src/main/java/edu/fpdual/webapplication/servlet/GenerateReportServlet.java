package edu.fpdual.webapplication.servlet;

import com.itextpdf.text.DocumentException;
import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.servlet.dto.ExpenseIncome;
import edu.fpdual.webapplication.utilities.reportpdf.ReportToPdf;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GenerateReportServlet", urlPatterns = {"/generate-report-servlet"})
public class GenerateReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ExpenseIncome> expenseIncomeList = new ArrayList<>();

        String tipoGasto = "Gasto";
        expenseIncomeList.add(new ExpenseIncome("01-06-2023", tipoGasto + " Compra del hogar", "100 €", "'Hogar'"));
        expenseIncomeList.add(new ExpenseIncome("02-06-2023", tipoGasto + " Vuelos", "150 €", "'Viaje'"));
        expenseIncomeList.add(new ExpenseIncome("03-06-2023", tipoGasto + " Vuelos", "150 €", "'Viaje'"));

        String tipoIngreso = "Ingreso";
        expenseIncomeList.add(new ExpenseIncome("01-06-2023", tipoIngreso + " Ahorro Dubai", "500 €", "Viaje"));
        expenseIncomeList.add(new ExpenseIncome("02-06-2023", tipoIngreso + " Casa rural", "300 €", "Ocio"));
        expenseIncomeList.add(new ExpenseIncome("03-06-2023", tipoIngreso + " Verano", "200 €", "Viaje"));

        try {
            request.getSession().setAttribute("ok", new ReportToPdf().createPDF(expenseIncomeList));
            response.sendRedirect(GlobalInfo.URL_JSP_REPORTS);
        } catch (DocumentException e) {
            request.getSession().setAttribute("error", "No se ha podido crear el reporte");
            response.sendRedirect(GlobalInfo.URL_JSP_REPORTS);
        }
    }
}
