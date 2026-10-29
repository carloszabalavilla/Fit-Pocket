<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>
<%@ page import="edu.fpdual.webapplication.dto.Currency" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Añadir Presupuesto</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>

        <div class="mainBlock">
            <div class="content">
                <h3 class="pageTitle">Nuevo <br/> Presupuesto</h3>
                <br/>
                <form method="POST" action="<%=GlobalInfo.URL_SERVLET_ADDBUDGET%>" class="form">
                    <label for="budgetNameInput">Nombre</label>
                    <br/>
                    <input type="text" id="budgetNameInput" name="budgetNameInput"/>
                    <br/>
                    <br/>
                    <label for="currencyInput">Moneda</label>
                    <br/>
                    <select id="currencyInput" name="currencyInput">
                        <%
                            List<Currency> currencyList = (List<Currency>) session.getAttribute("currencyList");
                            session.removeAttribute("currencyList");
                            if (currencyList != null && currencyList.size() > 0) {
                                for (Currency currency : currencyList) {
                        %>
                        <option value="<%=currency.getCurrencyId()%>"><%=currency.getCurrencyName()%>
                            (<%=currency.getCurrencySymbol()%>)
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <br/>
                    <br/>
                    <label for="budgetDescInput">Descripción</label>
                    <br/>
                    <textarea id="budgetDescInput" name="budgetDescInput" rows="5" cols="65"></textarea>
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
