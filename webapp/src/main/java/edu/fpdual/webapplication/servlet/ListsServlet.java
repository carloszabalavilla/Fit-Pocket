package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.client.*;
import edu.fpdual.webapplication.dto.*;
import edu.fpdual.webapplication.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@WebServlet(name = "ListsServlet", urlPatterns = {"/lists-servlet"})
public class ListsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatcherURLLists = "jsp/admin/database/lists.jsp";
        String emptyError = "No hay nada en esta lista.";

        List<String> objectList = switch (request.getParameter("classType")) {
            case "budget" -> getBudgetList();
            case "category" -> getCategoryList();
            case "expense" -> getExpenseList();
            case "income" -> getIncomeList();
            case "profile" -> getProfileList();
            case "report" -> getReportList();
            case "user" -> getUserList();
            default -> null;
        };
        request.setAttribute("error", emptyError);
        request.setAttribute("objectList", objectList);
        request.getRequestDispatcher(dispatcherURLLists).forward(request, response);
    }

    private <T> List<String> getStringList(List<T> objects, Function<T, String> toStringFunction) {
        List<String> stringList = new ArrayList<>();
        for (T object : objects) {
            stringList.add(toStringFunction.apply(object));
        }
        return stringList;
    }

    private List<String> getBudgetList() {
        BudgetService budgetService = new BudgetService(new BudgetClient());
        List<Budget> budgets = budgetService.getAllBudgets();
        return getStringList(budgets, Budget::toString);
    }

    private List<String> getCategoryList() {
        CategoryService categoryService = new CategoryService(new CategoryClient());
        List<Category> categories = categoryService.getAllCategories();
        return getStringList(categories, Category::toString);
    }

    private List<String> getExpenseList() {
        ExpenseService expenseService = new ExpenseService(new ExpenseClient());
        List<Expense> expenses = expenseService.getAllExpenses();
        return getStringList(expenses, Expense::toString);
    }

    private List<String> getIncomeList() {
        IncomeService incomeClient = new IncomeService(new IncomeClient());
        List<Income> incomes = incomeClient.getAllIncomes();
        return getStringList(incomes, Income::toString);
    }

    private List<String> getProfileList() {
        ProfileService profileService = new ProfileService(new ProfileClient());
        List<Profile> profiles = profileService.getAllProfiles();
        return getStringList(profiles, Profile::toString);
    }

    private List<String> getReportList() {
        ReportService reportService = new ReportService(new ReportClient());
        List<Report> reports = reportService.getAllReports();
        return getStringList(reports, Report::toString);
    }

    private List<String> getUserList() {
        UserService userService = new UserService(new UserClient());
        List<User> users = userService.getAllUsers();
        return getStringList(users, User::toString);
    }
}
