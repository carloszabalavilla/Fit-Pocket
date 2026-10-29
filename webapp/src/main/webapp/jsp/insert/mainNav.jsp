<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>
<%@ page import="edu.fpdual.webapplication.servlet.dto.Session" %>

<% Session thisSession = (Session) session.getAttribute(GlobalInfo.session); %>

<div class="mainNav">
    <div class="mainLinkNav">
        <ul>
            <li>
                <a class="active" href="<%=GlobalInfo.URL_JSP_HOME%>">Inicio</a>
            </li>
            <li>
                <a class="active" href="<%=GlobalInfo.URL_JSP_ABOUTUS%>">Nosotros</a>
            </li>
            <li>
                <a class="active" href="<%=GlobalInfo.URL_JSP_CONTACTUS%>">Contacto</a>
            </li>
            <% if (thisSession != null && thisSession.isAdmin()) { %>
            <li>
                <a class="active" href="<%=GlobalInfo.URL_JSP_CONTROLPANEL%>">Panel de administrador</a>
            </li>
            <% } %>
        </ul>
    </div>

    <% if (session.getAttribute(GlobalInfo.session) == null) { %>

    <div class="leftNav">
        <form method="get" action="<%=GlobalInfo.URL_JSP_LOGIN%>">
            <button type="submit" name="submitBtn" class="buttonB">Login</button>
        </form>
        <form method="get" action="<%=GlobalInfo.URL_JSP_REGISTER%>">
            <button type="submit" name="submitBtn" class="buttonB">Registro</button>
        </form>
    </div>

    <% } else { %>
    <div class="dropdown">
        <button class="dropBtn"><%= thisSession.getUserName() %>
        </button>
        <div class="dropdownContent">
            <form method="Get" action="<%= GlobalInfo.URL_JSP_PROFILE %>">
                <input type="submit" value="Perfil">
            </form>
            <form method="POST" action="<%= GlobalInfo.URL_SERVLET_LOGOUT %>">
                <input type="submit" value="Cerrar Sesión">
            </form>
        </div>
    </div>
    <% } %>
</div>
