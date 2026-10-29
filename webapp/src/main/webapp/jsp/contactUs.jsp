<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="insert/headTemplate.jsp" %>
    <title>Contacto</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>

</head>

<body>
<%@ include file="insert/mainNav.jsp" %>
<div class="container">
    <main>
        <div class="mainBlock">
            <div class="content">
                <h3 class="pageTitle">Contáctanos</h3>
                <br/>
                <form class="form" method="POST" action=<%=GlobalInfo.URL_SERVLET_CONTACTUS%>>
                    <label for="nameContact">Nombre</label>
                    <br/>
                    <input type="text" id="nameContact" name="nameContact"/>
                    <br/>
                    <br/>
                    <label for="emailContact">Correo Electrónico</label>
                    <br/>
                    <input type="email" id="emailContact" name="emailContact"/>
                    <br/>
                    <br/>
                    <label for="contactMessage">Mensaje</label>
                    <br/>
                    <textarea id="contactMessage" name="contactMessage" rows="5" cols="65"></textarea>
                    <br/>
                    <br/>
                    <% if (request.getAttribute("error") != null) {
                    %>
                    <p class="errorAlert"><%= request.getAttribute("error") %>
                    </p><br/>
                    <br/>
                    <% } else if (request.getAttribute("ok") != null) { %>
                    <p><%= request.getAttribute("ok") %>
                    </p>
                    <br/>
                    <% } %>
                    <button type="submit" name="submitBtn" value="" class="buttonC">Enviar</button>
                </form>
            </div>
        </div>
        <%@ include file="insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
