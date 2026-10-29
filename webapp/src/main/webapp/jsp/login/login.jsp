<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Login</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<section class="boxBody">
    <div>
        <a href="/ProyectoPrueba/" class="logoTxt">
            <h3 class="logoTxt">Fit-Pocket</h3>
        </a>
        <br/>
        <h3 class="H3Box">Entrar</h3>
    </div>
    <br/>
    <form method="POST" action=<%=GlobalInfo.URL_SERVLET_LOGIN%>>
        <% if (session.getAttribute(GlobalInfo.session) != null) {
            response.sendRedirect(GlobalInfo.URL_JSP_HOME);
        } %>
        <label for="userName">Nombre de usuario</label>
        <br/>
        <input type="text" id="userName" name="userName" placeholder="Nombre123" required/>
        <br/>
        <br/>
        <label for="userPassword">Contraseña</label>
        <br/>
        <input type="password" id="userPassword" name="userPassword" placeholder="Contraseña" required/>
        <br/>
        <br/>
        <% if (request.getAttribute("error") != null) {
        %>
        <p class="errorAlert"><%= request.getAttribute("error") %>
        </p><br/>
        <br/>
        <% } %>
        <p class="centerTxt"><a href=<%=GlobalInfo.URL_JSP_RESTOREPASSWORD%>>¿Olvidó la contraseña?</a></p>
        <br/>
        <input type="submit" value="Entrar" class="buttonA"/>
        <br/>
        <br/>
        <p class="centerTxt"><a href=<%=GlobalInfo.URL_JSP_REGISTER%>>¿No tiene cuenta? Cree una</a></p>
    </form>
</section>

</body>

</html>
