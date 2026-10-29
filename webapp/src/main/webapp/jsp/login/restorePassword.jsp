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
        <h3 class="H3Box">Restaurar Contraseña</h3>
    </div>
    <!-- Para que salga el error
    <div class="errorAlert">
        <p>E-Mail o contraseña incorrecto.</p>
    </div>-->
    <br/>
    <form method="post" action="<%=GlobalInfo.URL_SERVLET_RESTOREPASSWORD%>">
        <label for="email">Correo Electrónico</label>
        <br/>
        <br/>
        <input type="text" name="email" id="email" placeholder="nombre@ejemplo.com" required/>
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
        <button type="submit" name="submitBtn" value="Restablecer" class="buttonA">Enviar</button>
        <br/>
        <br/>
    </form>

</div>
</body>
</html>
