<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>
<%@ page import="edu.fpdual.webapplication.dto.Budget" %>
<%@ page import="edu.fpdual.webapplication.dto.Income" %>
<%@ page import="edu.fpdual.webapplication.dto.Expense" %>
<%@ page import="java.util.List" %>



<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos Presupuesto</title>

    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css" />

</head>

<body>
    <div class="container">
       <%@ include file="../insert/header.jsp" %>
        <main>
            <%@ include file="../insert/mainNav.jsp" %>

            <div class="mainBlock">
                <div class="contentList">
                    <% Budget budget = (Budget) session.getAttribute("budget"); %>
                    <% double balance = (double) session.getAttribute("balance"); %>
                    <h3 class="pageTitle1"><%= budget.getBudgetName() %></h3>
                    <div class="descriptionTextBudget">
                        <p><span>Descripcion:</span> <%= budget.getDescription() %></p>
                        <p><span>Fecha:</span> <%= budget.getCreationDate() %></p>
                        <p><span>Balance:</span> <%= balance %></p>
                    </div>
                    <div class="budgetButtons">
                        <form method="GET" action="<%=GlobalInfo.URL_SERVLET_ADDINCOME %>">
                            <button type="submit" name="incomeBtn" value="<%= budget.getBudgetId() %>" class="buttonD">Añadir Ingreso</button>
                        </form>
                        <form method="GET" action="<%=GlobalInfo.URL_SERVLET_ADDEXPENSE%>">
                            <button type="submit" name="expenseBtn" value="<%= budget.getBudgetId() %>" class="buttonD">Añadir Gasto</button>
                        </form>
                        <form method="POST" action="<%=GlobalInfo.URL_SERVLET_DELETEBUDGET%>">
                            <button type="submit" name="deleteBtn" value="<%= budget.getBudgetId() %>" class="buttonD">Borrar Presupuesto</button>
                        </form>
                    </div>

                    <%List<Income> incomeList = (List<Income>) budget.getIncomeList();
                    if(incomeList != null && incomeList.size() > 0) {%>
                    <h3>Lista de Ingresos</h3>
                        <%for (Income income : incomeList) {%>
                            <div class="listElement2">
                                <h4><%= income.getIncomeName() %></h4>
                                <div class="descriptionTextBudget">
                                    <p>Descripcion: <%= income.getDescription() %></p>
                                    <p>Categoría: <%= income.getCategoryId() %></p>
                                    <p>Cantidad: <%= income.getAmount() %></p>
                                    <p>Recurrente: <%= income.isRecurrent() %></p>
                                    <p>Fecha: <%= income.getCreationDate() %></p>
                                </div>
                                <form method="POST" action="<%=GlobalInfo.URL_SERVLET_DELETEINCOME%>">
                                    <button type="submit" name="deleteBtnIncome" value="<%= income.getIncomeId() %>" class="buttonD">Borrar Ingreso </button>
                                </form>
                            </div>
                        <%}
                    }%>

                    <%List<Expense> expenseList = (List<Expense>) budget.getExpenseList();
                    if(expenseList != null && expenseList.size() > 0) {%>
                        <h3>Lista de Gastos</h3>
                        <%for (Expense expense : expenseList) {%>
                            <div class="listElement2">
                                <h4><%= expense.getExpenseName() %></h4>
                                <div class="descriptionTextBudget">
                                    <p>Descripcion: <%= expense.getDescription() %></p>
                                    <p>Categoría: <%= expense.getCategoryId() %></p>
                                    <p>Cantidad: <%= expense.getAmount() %></p>
                                    <p>Recurrente: <%= expense.isRecurrent() %></p>
                                    <p>Fecha: <%= expense.getCreationDate() %></p>
                                </div>
                                <form method="POST" action="<%=GlobalInfo.URL_SERVLET_DELETEEXPENSE%>">
                                    <button type="submit" name="deleteBtnExpense" value="<%= expense.getExpenseId() %>" class="buttonD">Borrar Gasto </button>
                                </form>
                            </div>
                        <%}
                    }%>
                </div>
            </div>
            <%@ include file="../insert/footer.jsp" %>
        </main>
    </div>
</body>

</html>
