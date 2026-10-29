<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../../insert/headTemplate.jsp" %>
    <title>Lista de Objetos</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="container">
    <%@ include file="../../insert/header.jsp" %>
    <main>
        <%@ include file="../../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="contentList">
                <h3 class="pageTitle1">Listas de Objetos</h3>

                <form method="post" action="<%=GlobalInfo.URL_SERVLET_LISTS%>">
                    <div class="radio-container">
                        <label>
                            <input type="radio" name="classType" id="budget" value="budget" checked>
                            Presupuestos
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="category" value="category">
                            Categorias
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="expense" value="expense">
                            Gastos
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="income" value="income">
                            Ingresos
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="profile" value="profile">
                            Perfiles
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="report" value="report">
                            Reportes
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="user" value="user">
                            Usuarios
                        </label>
                        <br/>
                        <br/>
                        <label>
                            <input type="radio" name="classType" id="empty" value="empty">
                            Vacio
                        </label>
                        <br/>
                        <br/>
                        <button type="submit" class="buttonB" >Mostrar Lista</button>
                    </div>
                </form>
                <br/>
                <form method="post" action="<%=GlobalInfo.URL_JSP_CONTROLPANEL%>" class="form">
                    <input type="submit" name="submitBtn" value="Volver" class="buttonB">
                </form>
                <div class="">
                    <% List<String> objectList = (List<String>) request.getAttribute("objectList"); %>
                    <% if (objectList != null && !objectList.isEmpty()) { %>
                    <table class="tableContainer">
                        <tr>
                            <th>Objeto</th>
                        </tr>
                        <% for (String obj : objectList) { %>
                        <tr>
                            <td><%= obj %>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                    <% } else if (request.getAttribute("error") != null) { %>
                    <p><%= request.getAttribute("error") %>
                    </p>
                    <% } %>
                </div>

            </div>
        </div>
    </main>
</div>




</body>

</html>
