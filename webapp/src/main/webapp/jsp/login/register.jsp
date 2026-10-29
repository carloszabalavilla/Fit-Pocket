<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>


<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Register Fit-Pocket</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<section class="boxBody">
    <div>
        <a href="/ProyectoPrueba/" class="logoTxt">
            <h3 class="logoTxt">Fit-Pocket</h3>
        </a>
        <br/>
        <h3 class="H3Box">Registro</h3>
    </div>

    <br/>
    <form method="post" action="<%=GlobalInfo.URL_SERVLET_REGISTER%>">
        <label for="userName">Nombre de usuario: </label>
        <br/>
        <input type="text" name="userName" id="userName" placeholder="Nombre123" required>
        <br/>
        <br/>
        <label for="email">Correo Electrónico</label>
        <br/>
        <input type="email" id="email" name="email" placeholder="nombre@ejemplo.com" required/>
        <br/>
        <br/>
        <label for="userPassword">Contraseña</label>
        <br/>
        <input type="password" id="userPassword" name="userPassword" placeholder="Contraseña" required/>
        <br/>
        <br/>
        <!-- Mensaje de error en caso de que haya algo incorrecto-->
        <% if (request.getAttribute("error") != null) { %>
        <div class="errorAlert">
            <p><%= request.getAttribute("error") %>
            </p>
        </div>
        <br/>
        <% } %>
        <% if (request.getAttribute("ok") != null) { %>
        <p><%= request.getAttribute("ok") %>
        </p>
        <br/>
        <br/>
        <a class="centerTxt" href="<%=GlobalInfo.URL_JSP_LOGIN%>">Volver al login</a>
        <br/>
        <%} else { %>
        <button type="submit" name="submitBtn" value="Crear usuario" class="buttonA">Registrar</button>
        <br/>
        <br/>
        <p class="centerTxt"><a href=<%=GlobalInfo.URL_JSP_LOGIN%>>¿Ya tiene cuenta?</a></p>
        <br/>
        <% } %>
    </form>
</section>
</body>
</html>
