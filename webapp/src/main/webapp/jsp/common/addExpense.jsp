<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>
<%@ page import="edu.fpdual.webapplication.dto.Category" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Añadir Gasto</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>


</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>

    <main>
        <%@ include file="../insert/mainNav.jsp" %>

        <div class="mainBlock">
            <div class="content">
                <h3 class="pageTitle">Nuevo <br/> Gasto</h3>
                <br/>
                <form method="post" action="<%=GlobalInfo.URL_SERVLET_ADDEXPENSE%>" class="form">
                    <label for="expenseNameInput">Nombre</label>
                    <br/>
                    <input type="text" id="expenseNameInput" name="expenseNameInput" required/>
                    <br/>
                    <br/>
                    <label for="expenseDescInput">Descripción</label>
                    <br/>
                    <textarea id="expenseDescInput" name="expenseDescInput" rows="5"
                              cols="65"></textarea>
                    <br/>
                    <br/>
                    <label for="expenseAmountInput">Cantidad</label>
                    <br/>
                    <input type="number" id="expenseAmountInput" name="expenseAmountInput" required/>
                    <br/>
                    <br/>
                    <label for="categoryInput">Categoría</label>
                    <br/>
                    <select id="categoryInput" name="categoryInput" required>
                    <%
                        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
                        session.removeAttribute("categoryList");
                        if (categoryList != null && categoryList.size() > 0) {
                            for (Category category : categoryList) {
                    %>
                    <option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                    </option>
                    <%
                            }
                        }
                    %>
                    </select>
                    <br/>
                    <br/>
                    <input type="checkbox" id="recurrentExpense" name="recurrentExpense">
                    <label for="recurrentExpense"> Hacer gasto mensual</label>
                    <br/>
                    <br/>
                    <button type="submit" name="submitBtn" value="Guardar" class="buttonC">Crear</button>
                </form>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
