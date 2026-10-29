<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Login Fit-Pocket</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="boxBody">
    <div>
        <a href="/ProyectoPrueba/" class="logoTxt">
            <h3 class="logoTxt">Fit-Pocket</h3>
        </a>
        <br/>
        <h3>Restaurar Contraseña</h3>
    </div>
    <br/>
    <form method="post" action="<%=GlobalInfo.URL_SERVLET_UPDATEPASSWORD%>">
        <label for="code">Antigua contraseña</label>
        <br/>
        <br/>
        <input type="text" name="code" id="code" placeholder="Password" required/>
        <br/>
        <br/>
        <label for="newPassword">Nueva contraseña</label>
        <br/>
        <br/>
        <input type="text" name="newPassword" id="newPassword" placeholder="Password" required/>
        <br/>
        <br/>
        <!-- Mensaje de error en caso de que haya algo incorrecto-->
        <% if (request.getAttribute("error") != null) { %>
        <div class="errorAlert">
            <p style="color:red"><%= request.getAttribute("error") %>
            </p></div>
        <br/>
        <% } else if (request.getAttribute("ok") != null) { %>
        <p><%= request.getAttribute("ok") %>
        </p>
        <%}%>
        <br/>
        <br/>
        <%if (request.getAttribute("ok") == null) { %>
        <button type="submit" name="submitBtn" value="Aceptar" class="buttonA">Enviar</button>
        <% } else { %>
        <a href="<%=GlobalInfo.URL_JSP_LOGIN%>">Volver al login</a>
        <% } %>
        <br/>
        <br/>
    </form>
</div>

</body>
</html>
