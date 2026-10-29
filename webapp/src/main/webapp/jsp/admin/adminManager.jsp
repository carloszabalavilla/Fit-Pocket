<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Manejo de Administrador</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="content">
             <h3 class="pageTitle">Manejo de<br/> administradores</h3>

                    <form method="post" action="<%=GlobalInfo.URL_SERVLET_ADMINMANAGER%>" class="form">
                        <label for="userName">Nombre de usuario:</label>
                        <input type="text" name="userName" id="userName" required>
                        <br/>
                        <% if (request.getAttribute("error") != null) { %>
                        <p style="color:red"><%= request.getAttribute("error") %>
                        </p><br/>
                        <% } else if (request.getAttribute("ok") != null) { %>
                        <p><%= request.getAttribute("ok") %>
                        </p><br/>
                        <% } %>
                        <br/>
                        <button type="submit" name="action" value="update" class="buttonC">Crear admin</button>
                        <button type="submit" name="action" value="delete" class="buttonC">Borrar admin</button>
                        <br/>
                        <br/>
                    </form>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>



</div>
</body>

</html>
